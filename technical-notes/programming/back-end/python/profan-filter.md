# Profan Filter

## Simple Method

```python
class ProfanFilter():
    def __init__(self):
        self.keywords = set([])

    def parse(self, path):
        for keyword in open(path):
            self.keywords.add(keyword.strip().lower())

    def filter(self, message, replacement="*"):
        message = str(message).lower()
        for keyword in self.keywords:
            message = message.replace(keyword, replacement)
        return message
```

Use the function as the following way.

```python
profan_filter = ProfanFilter()
profan_filter.parse('rating/profane')
pprint(profan_filter.filter('hello sexy baby 幹雞掰 操''))
```

`rating/profane` is a dictionary which is located in the same folder where the mentioned Views are. The dictionary is just a simple file that separate words with `\n`. This is how `rating/profane` will look like.

```text
幹
操
幹你娘
雞掰
操雞掰
王八蛋
```

## Mid Method

```python
class ProfanBSFilter():
    def __init__(self):
        self.keywords = []
        self.keyword_sets = set([])
        self.back_sorted_dict = defaultdict(set)
        self.pattern_en = re.compile(r'^[0-9a-zA-Z]+$')

    def add(self, keyword):
        if not isinstance(keyword, str):
            keyword = keyword.decode('utf-8')

        keyword = keyword.lower()
        if keyword not in self.keyword_sets:
            self.keywords.append(keyword)
            self.keyword_sets.add(keyword)
            index = len(self.keywords) - 1
            for word in keyword.split():
                if self.pattern_en.search(word):
                    self.back_sorted_dict[word].add(index)
                else:
                    for character in word:
                        self.back_sorted_dict[character].add(index)

    def parse(self, path):
        with open(path, "r") as keyword_book:
            for keyword in keyword_book:
                self.add(keyword.strip())

    def filter(self, message, replacement="*"):
        if not isinstance(message, str):
            message = message.decode('utf-8')
        message = message.lower()
        for word in message.split():
            if self.pattern_en.search(word):
                for index in self.back_sorted_dict[word]:
                    message = message.replace(
                        self.keywords[index], replacement)
            else:
                for character in word:
                    for index in self.back_sorted_dict[character]:
                        message = message.replace(
                            self.keywords[index], replacement)
        return message
```

Use the function as the following way.

```python
profan_bs_filter = ProfanBSFilter()
profan_bs_filter.add('sexy')
profan_bs_filter.parse('rating/profane')
pprint(profan_bs_filter.filter('hello sexy baby 幹雞掰 操'))
```

## Algorithm Method

```python
class ProfanDFAFilter():
    def __init__(self):
        self.keyword_chains = {}
        self.delimit = '\x00'

    def add(self, keyword):
        if not isinstance(keyword, str):
            keyword = keyword.decode('utf-8')
        keyword = keyword.lower()
        characters = keyword.strip()
        if not characters:
            return
        level = self.keyword_chains
        for i in range(len(characters)):
            if characters[i] in level:
                level = level[characters[i]]
            else:
                if not isinstance(level, dict):
                    break
                for index in range(i, len(characters)):
                    level[characters[index]] = {}
                    last_level, last_character = level, characters[index]
                    level = level[characters[index]]
                last_level[last_character] = {self.delimit: 0}
                break
        if i == len(characters) - 1:
            level[self.delimit] = 0

    def parse(self, path):
        with open(path) as keyword_book:
            for keyword in keyword_book:
                self.add(keyword.strip())

    def filter(self, message, replacement="*"):
        if not isinstance(message, str):
            message = message.decode('utf-8')
        message = message.lower()
        ret = []
        start = 0
        while start < len(message):
            level = self.keyword_chains
            step_ins = 0
            for character in message[start:]:
                if character in level:
                    step_ins += 1
                    if self.delimit not in level[character]:
                        level = level[character]
                    else:
                        ret.append(replacement * step_ins)
                        start += step_ins - 1
                        break
                else:
                    ret.append(message[start])
                    break
            else:
                ret.append(message[start])
            start += 1
        return ''.join(ret)
```

Use the function as the following way.

```python
profan_dfa_filter = ProfanDFAFilter()
profan_dfa_filter.add('sexy')
profan_dfa_filter.parse('rating/profane')
pprint(profan_dfa_filter.filter('hello sexy baby 幹雞掰 操'))
```


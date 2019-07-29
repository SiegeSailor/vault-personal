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
profan_filter.filter('幹什麼')
pprint(profan_filter.filter('幹什麼'))
```

`rating/profane` is a dictionary which is located in the same folder where the mentioned Views are. The dictionary is just a simple file that separate words with `\n`.


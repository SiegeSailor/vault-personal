# HTML Output with Variable

## Overall

Ways to render HTML with variables.

#### Template

```python
from django.template.loader import render_to_stringrendered = render_to_string('my_template.html', { 'foo': 'bar' })
```

#### Plain HTML

```python
from django.template import Context, Templatet = Template("My name is {{ my_name }}.")c = Context({"my_name": "Adrian"})output = t.render(c)
```


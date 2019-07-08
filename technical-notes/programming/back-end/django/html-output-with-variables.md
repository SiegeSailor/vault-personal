# HTML Output with Variables

## Overall

Ways to render HTML with variables.

#### Template

```python
from django.template.loader import render_to_string
rendered = render_to_string('my_template.html', { 'foo': 'bar' })
```

#### Plain HTML

```python
from django.template import Context, Template
t = Template("My name is {{ my_name }}.")
c = Context({"my_name": "Adrian"})
output = t.render(c)
```


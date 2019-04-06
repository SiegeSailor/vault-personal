# JS

DOM javascript can't just use addClass \(Classname instead\) to an DOM in an array  
\(Target\[i\].Classname += 'NavBar\_Sub\_Hover';\)  
\(Target\[i\].addClass\('NavBar\_Sub\_Hover''\);

but jquery can use $\(Target\[i\]\).addClass\('NavBar\_Sub\_Hover'\);



階層選擇與子元素選擇

document.querySelector\(".ImageBox"\).querySelector\('ul'\).querySelectorAll\('li'\);

for \(var i = 0; i &lt; ImageBoxLi.length; i = i + 1\) { var ImageBoxH3\_TextContent = \[\]; ImageBoxH3\_TextContent = ImageBoxLi\[i\].childNodes\[1\].textContent; };





var ListBoxTotal = document.querySelectorAll\('.ListBox\_Content'\); for \(var i = 0; i &lt; ListBoxTotal.length; i = i + 1\) { var AssignedListBox = ListBoxTotal\[i\].id; document.getElementById\(AssignedListBox\).style.display = "none"; };

to make this function work doesn't need to use "'" + var AssignedListBox = ListBoxTotal\[i\].id; "'", js automatically add it



jquery: don't use transition at slide




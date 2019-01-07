// Full Height
document.querySelector('.header').style.height = window.innerHeight+"px"
window.onresize = function(){
   document.querySelector('.header').style.height = window.innerHeight+"px"
}

// Print
// document.querySelector('#print').onclick = function (event) {
//    event.preventDefault();
//    window.print();
// }
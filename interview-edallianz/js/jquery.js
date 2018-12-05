$(document).ready(function() {
    // Very beginning alert for looks for a job.
    $('.alert').hide();
    $('.alert').delay(750).fadeIn();
    $('.alert').delay(10000).fadeOut();
    $('.alert .fas').click(function(){
        $(this).parent().fadeOut();
    });
    // Disable buttons for Front-End skills section.
    $('.front-end-skills li a').click(function(event){
        event.preventDefault();
    })
});
$(document).ready(function () {
    // Very beginning alert for looks for a job.
    $('.alert').hide();
    $('.alert').delay(750).fadeIn();
    $('.header .alert i').click(function () {
        $(this).parent().fadeOut();
    });

    // Function for popup windows.
    $('.mail').hide();
    $('#tomail').click(function () {
        $('.mail').fadeIn();
    });
    $('.mail .text i').click(function () {
        $(this).parent().parent().fadeOut();
    });

    // Disable buttons for Front-End skills section.
    $('.front-end-skills li a').click(function (event) {
        event.preventDefault();
    });

    // Disable buttons for Graphic Design skills section.
    $('.graphic-design-skills li a').click(function (event) {
        event.preventDefault();
    });

    // Diesable buttons for Digital Marketing skills section.
    $('.digital-marketing-skills li a').click(function (event) {
        event.preventDefault();
    });

    // Plugin / Masonry
    var $grid = $('.grid').masonry({
        itemSelector: '.grid-item',
        columnWidth: '.grid-sizer',
        percentPosition: true
    });
    $grid.on('click', '.grid-item', function () {
        // change size of item via class
        // $(this).toggleClass('grid-item--gigante');
        // trigger layout
        $grid.masonry();
    });

    // Plugin / Swiper
    // http://idangero.us/swiper/get-started/
    var swiper = new Swiper('.swiper-container', {
        pagination: {
            el: '.swiper-pagination',
            type: 'progressbar',
        },
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
    });


    // About Me / Experience / Progress-bar
    var showskillsection = true;
    $(window).scroll(function (event) {
        var scrollposition = $(window).scrollTop();
        var windowheight = $(window).height();
        var skillsectiontop = $('#AboutMe-Experience').offset().top;
        if (skillsectiontop <= (scrollposition + windowheight / 2) && showskillsection) {
            $('#AboutMe-Experience .progress-bar').each(function () {
                var thisvalue = $(this).data('progress');
                $(this).css('width', thisvalue + '%');
                showskillsection = false;
            });
        }
    });

    // Scroll Top Button
    $('#button-animate-scrolltop').click(function (event) {
        $('html,body').animate({
            scrollTop: 0
        }, 1000);
    });

    // Scroll Down Button
    $('.scroll-down a').click(function () {
        event.preventDefault();
        var target = $(this).attr('href');
        var targetposition = $(target).offset().top;
        $('html,body').animate({
            scrollTop: targetposition
        }, 1000);
    });

    // Menu Scroll
    $('.navbar ul li a').click(function () {
        event.preventDefault();
        var target = $(this).attr('href');
        var targetposition = $(target).offset().top;
        $('html,body').animate({
            scrollTop: targetposition
        }, 1000);
    });

    // Mobile Menu Scroll
    $('.navbar-mobile ul li a').click(function () {
        event.preventDefault();
        var target = $(this).attr('href');
        var targetposition = $(target).offset().top;
        $('html,body').animate({
            scrollTop: targetposition
        }, 1000);
    });

    // Menu Scroll Hover
    $(window).scroll(function (event) {
        var scrollposition = $(window).scrollTop();

        $('.navbar ul li a').each(function () {
            var target = $(this).attr('href');
            var targetposition = $(target).offset().top;
            var targetheight = $(target).outerHeight();
            if (targetposition - 1 <= scrollposition && (targetposition - 1 + targetheight) > scrollposition) {
                $('.navbar ul li a').removeClass('.navbar-active');
                $(this).addClass('navbar-active');
            } else {
                $(this).removeClass('navbar-active');
            }
        });
    });

    // Alert Scroll
    $('.alert a').click(function () {
        event.preventDefault();
        var target = $(this).attr('href');
        var targetposition = $(target).offset().top;
        $('html,body').animate({
            scrollTop: targetposition
        }, 1000);
        $('.alert').fadeOut();
    });

    // Achievement
    // $('.achievement p').each(function () {
    //     $(this).hide();
    // });
    // $(window).scroll(function (event) {
    //     var scrollposition = $(window).scrollTop();

    //     $('.achievement p').each(function () {
    //         var targetposition = $(this).offset().top;
    //         var targetheight = $(this).outerHeight();
    //         if (targetposition <= scrollposition && (targetposition + targetheight) > scrollposition) {
    //             $(this).fadeIn();
    //             console.log("dsa");
    //         }
    //     });
    // });

    // Menu Animate
    $('#navbar-showmenu').click(function () {
        $('#navbar-showmenu').toggleClass('pulse animated');
    }, function () {
        $('#navbar-showmenu').toggleClass('pulse animated');
    });
    $('#navbar-showmenu-back').click(function () {
        $('#navbar-showmenu-back').toggleClass('pulse animated');
    }, function () {
        $('#navbar-showmenu-back').toggleClass('pulse animated');
    });
    // Menu Mobile Switch
    $('#navbar-showmenu').click(function (event) {
        event.preventDefault();
        $('.navbar-mobile').toggleClass('navbar-mobile-active');
        $('#navbar-showmenu').toggle();
        $('#navbar-showmenu-back').toggle();
    });
    $('#navbar-showmenu-back').click(function (event) {
        event.preventDefault();
        $('.navbar-mobile').toggleClass('navbar-mobile-active');
        $('#navbar-showmenu').toggle();
        $('#navbar-showmenu-back').toggle();
    });

    // Plugin / Animate Number
    // https://github.com/aishek/jquery-animateNumber
    // animateNumber accepts same arguments, as animate does
    // it adds only 'number' and 'numberStep' params
    var shownumbers = true;
    $(window).scroll(function (event) {
        var scrollposition = $(window).scrollTop();
        var windowheight = $(window).height();
        var insighttop = $('.insight').offset().top;
        if (insighttop <= (scrollposition + windowheight / 2) && shownumbers) {
            $('#pageviewsnumber').animateNumber({
                number: 264,
            }, 1800);
            $('#pagelikesnumber').animateNumber({
                number: 3726,
            }, 1800);
            $('#pagereachnumber').animateNumber({
                number: 19695,
            }, 1800);
            $('#postengagementsnumber').animateNumber({
                number: 1115,
            }, 1800);
            shownumbers = false;
        }
    });
});
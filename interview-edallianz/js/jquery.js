$(document).ready(function () {
    // Very beginning alert for looks for a job.
    $('.alert').hide();
    $('.alert').delay(750).fadeIn();
    $('.alert').delay(10000).fadeOut();
    $('.header .alert .fas').click(function () {
        $(this).parent().fadeOut();
    });

    // Disable buttons for Front-End skills section.
    $('.front-end-skills li a').click(function (event) {
        event.preventDefault();
    });

    // Disable buttons for Graphic Design skills section.
    $('.graphic-design-skills li a').click(function (event) {
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


    // 進度條的顯示與否設定
    // var showskillsection = true;
    // $(window).scroll(function (event) {
    //     // 定義現在視窗的滑動位置
    //     var scrollposition = $(window).scrollTop();
    //     // 取得現在視窗的高度
    //     var windowheight = $(window).height();
    //     // 取得目標y軸高度
    //     var skillsectiontop = $('#AboutMe-E').offset().top;

    //     // 目標y軸比視窗目前位置小或一樣，代表視窗目前位置在目標下方
    //     // 因為預期是滾到就會有效果，因此把一半的視窗高度加在視窗目前位置，
    //     // 以提早半個視窗
    //     if (skillsectiontop <= (scrollposition + windowheight / 2) && showskillsection) {
    //         // 對每個進度條的data數值定義
    //         $('#skillsection .progress-bar').each(function () {
    //             var thisvalue = $(this).data('progress');
    //             // 進度條數值動作
    //             $(this).css('width', thisvalue + '%');
    //             // 進度條的顯示與否設定，開關，避免重複設定寬度
    //             showskillsection = false;
    //         });
    //     }
    // });
});
$(function () {
    
'use strict';

// Navbar
var  height = $('.navbar').height();
$(window).scroll(function(){
    if($(this).scrollTop() > height){
        $('.navbar').addClass('fixed');
    }else{
        $('.navbar').removeClass('fixed');
    }
});


// Favorite Button - Heart
$('.favme').click(function() {
	$(this).toggleClass('active1');
});

/* when a user clicks, toggle the 'is-animating' class */
$(".favme").on('click touchstart', function(){
  $(this).toggleClass('is_animating');
});

/*when the animation is over, remove the class*/
$(".favme").on('animationend', function(){
  $(this).toggleClass('is_animating');
});

});


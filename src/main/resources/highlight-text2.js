

jQuery('*').mouseenter(function(){
    if (jQuery(this).text() !== "" && jQuery(this).children().length == 0) {
        this.oldColor = jQuery(this).css('background-color');
        jQuery(this).css({'background-color': 'yellow'});
    }

}).mouseleave(function(){
    if (jQuery(this).text() !== "" && jQuery(this).children().length == 0) {

        jQuery(this).css({'background-color': this.oldColor});
    }
}).click(function() {
    if (jQuery(this).text() !== "" && jQuery(this).children().length == 0) {





        jQuery(this).addClass(jQuery("#article-labeler-mode").html());
    }
});
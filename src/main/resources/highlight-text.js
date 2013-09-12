jQuery('img, a, p, pre, span, h1, h2, h3, h4, h5, h6, li, blockquote, dd, dt').attr("disabled", "disabled").mouseenter(function(){

    this.oldColor = jQuery(this).css('background-color');
    jQuery(this).css({'background-color': 'yellow'});

}).mouseleave(function(){

    jQuery(this).css({'background-color': this.oldColor});


}).click(function() {

    jQuery(this).removeClass("article-labeler-article-text article-labeler-article-title article-labeler-article-image article-labeler-article-image-caption article-labeler-article-byline")
    .addClass(jQuery("#article-labeler-mode").html());

     if (jQuery("#article-labeler-mode").html() === "article-labeler-article-text")
                this.tagged = "green";
            if (jQuery("#article-labeler-mode").html() === "article-labeler-article-title")
                this.tagged = "blue";
            if (jQuery("#article-labeler-mode").html() === "article-labeler-article-image")
                this.tagged = "red";
            if (jQuery("#article-labeler-mode").html() === "article-labeler-article-image-caption")
                this.tagged = "purple";
            if (jQuery("#article-labeler-mode").html() === "article-labeler-article-byline")
                this.tagged = "pink";

            if (jQuery("#article-labeler-mode").html() === "") {
                    jQuery(this).css({'border': '0px'});
                } else {
                    jQuery(this).css({'border': '2px solid '+this.tagged});
                }
    return false;
});

jQuery('div').mouseenter(function(){
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
        jQuery(this).removeClass("article-labeler-article-text article-labeler-article-title article-labeler-article-image article-labeler-article-image-caption article-labeler-article-byline")
        .addClass(jQuery("#article-labeler-mode").html());

        if (jQuery("#article-labeler-mode").html() === "article-labeler-article-text")
            this.tagged = "green";
        if (jQuery("#article-labeler-mode").html() === "article-labeler-article-title")
            this.tagged = "blue";
        if (jQuery("#article-labeler-mode").html() === "article-labeler-article-image")
            this.tagged = "red";
        if (jQuery("#article-labeler-mode").html() === "article-labeler-article-image-caption")
            this.tagged = "purple";
        if (jQuery("#article-labeler-mode").html() === "article-labeler-article-byline")
            this.tagged = "pink";

        if (jQuery("#article-labeler-mode").html() === "") {
            jQuery(this).css({'border': '0px'});
        } else {
            jQuery(this).css({'border': '2px solid '+this.tagged});
        }

    }
});
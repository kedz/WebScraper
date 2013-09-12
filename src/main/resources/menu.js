var div = jQuery('<div id="article-labeler-menu" />').appendTo('body');
jQuery('<div id="close-article-labeler" />').appendTo('body');

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Clear").click(function(){
            jQuery("#article-labeler-mode").html("");
        }).appendTo(jQuery(div));


jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Article Text").click(function(){
            jQuery("#article-labeler-mode").html("article-labeler-article-text");
        }).appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Article Title").click(function(){
            jQuery("#article-labeler-mode").html("article-labeler-article-title");
        }).appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Article Image").click(function(){
            jQuery("#article-labeler-mode").html("article-labeler-article-image");
        }).appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Article Image Caption").click(function(){
            jQuery("#article-labeler-mode").html("article-labeler-article-image-caption");
        }).appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("Byline").click(function(){
            jQuery("#article-labeler-mode").html("article-labeler-article-byline");
        }).appendTo(jQuery(div));


jQuery('<span id="article-labeler-mode">article-labeler-article-text<span>').appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("next").click(function(){
            jQuery("#article-labeler-mode").html("next");
        }).appendTo(jQuery(div));

jQuery('<input></input>').attr({'type': 'button','class': 'labeler-button'}).val("stop").click(function(){
            jQuery("#close-article-labeler").html("close");
        }).appendTo(jQuery(div));



jQuery(div).css({
    'position': 'fixed',
    'top': '0px',
    'display': 'block',
    'background-color': 'white',
    'width': '100%',
    'height': '100px',
    'z-index': '999999',
    'border': '1px dotted black'

});

jQuery('.labeler-button').css({'padding': '0px 5px 0px 5px', 'margin': '5px 5px 0px 5px'});
jQuery('#article-labeler-mode').css({
    'padding': '0px 5px 0px 5px',
    'margin': '0px 5px 0px 5px',
    'border': '1px solid black'});


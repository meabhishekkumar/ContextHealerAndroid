(function(window, undefined) {

    /*********************** START STATIC ACCESS METHODS ************************/

    jQuery.extend(jimMobile, {
        "loadScrollBars": function() {
            jQuery(".s-699a21b9-935a-4a51-aac9-bfe19e98fcb9 .ui-page").overscroll({ showThumbs:true, direction:'vertical' });
            jQuery(".s-ae107195-a6ca-4cab-a319-30ca3c5e345a .ui-page").overscroll({ showThumbs:true, direction:'vertical' });
            jQuery(".s-ae107195-a6ca-4cab-a319-30ca3c5e345a #s-Category_1").overscroll({ showThumbs:false, direction:'vertical' });
            jQuery(".s-8177edc8-6f73-4b73-8b53-c6ca5e039ca5 .ui-page").overscroll({ showThumbs:true, direction:'vertical' });
            jQuery(".s-d12245cc-1680-458d-89dd-4f0d7fb22724 .ui-page").overscroll({ showThumbs:true, direction:'vertical' });
            jQuery(".s-be8c5242-3b77-42be-b6cc-09dc740f9bbd .ui-page").overscroll({ showThumbs:true, direction:'vertical' });
         }
    });

    /*********************** END STATIC ACCESS METHODS ************************/

}) (window);
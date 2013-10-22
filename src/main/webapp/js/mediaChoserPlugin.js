(function($) {
    // Define the hello button
    $.cleditor.buttons.media = {
        name: "media",
        image: "../../images/images2.png",
        title: "Choose an image from the media library",
        command: "inserthtml",
        popupName: "media",
        popupClass: "cleditorPrompt",
        popupContent: "Image URL:<br><input type=hidden size=10 id='imgToInsert'><br><div id='imgs'></div><input type=button id='next' value='Next'><br><input type=button id='submit' value=Submit>",
        buttonClick: mediaClick
    };

    // Add the button to the default controls before the bold button
    $.cleditor.defaultOptions.controls = $.cleditor.defaultOptions.controls
            .replace("image", "image media");

    var page = 0;

    function addImgClickHandler() {
        $('#imgs img').click(function(img) {
            console.log(img.srcElement.getAttribute('fullsrc'));
            $('#imgToInsert').val(img.srcElement.getAttribute('fullsrc'));
            $('#submit').click();
        });
    }

    // Handle the hello button click event
    function mediaClick(e, data) {
        page = 0;
        $('#imgs').load('/admin/mediaSelector', addImgClickHandler);

        $(data.popup).children("#next").click(function(e) {
            $('#imgs').load('/admin/mediaSelector?page=' + ++page, addImgClickHandler);
        });

        // Wire up the submit button click event
        $(data.popup).children("#submit")
                .unbind("click")
                .bind("click", function(e) {
                    // Get the editor
                    var editor = data.editor;

                    // Get the full url of the image clicked
                    var fullUrl = $(data.popup).find("#imgToInsert").val();

                    // Insert the img tag into the document
                    var html = "<img src='" + fullUrl + "'>";
                    editor.execCommand(data.command, html, null, data.button);

                    // Hide the popup and set focus back to the editor
                    editor.hidePopups();
                    editor.focus();
                });
    }
})(jQuery);
$(document).ready(function() {
    var isExistingArticle = $('#titleInput').val().length > 0;

    $(".chzn-select").chosen();

    var editor = $("#content").cleditor({
        width: 600,
        height: 400,
        useCSS: true,
        docCSSFile: '/css/wysiwyg.css',
        styles: [
            ["Paragraph", "<p>"],
            ["Header 1", "<h1>"],
            ["Header 2", "<h2>"],
            ["Header 3", "<h3>"],
            ["Header 4","<h4>"],
            ["Code sample", "<pre>"],
            ["Quote", "<blockquote>"]
        ]
    });

    $('#titleInput').blur(function(evt) {
        var title = $(this).val();
        if (title.length == 0) {
            $('#titleInputError').css('display', 'inline-block');
            $('#submitButton').attr('disabled', 'true');
        } else {
            if (!isExistingArticle) {
                $.ajax({
                    url: '/admin/titleExists',
                    data: {'title': title},
                    success: function(msg) {
                        if (msg == 'true') {
                            $('#titleInputError').css('display', 'inline-block');
                            $('#submitButton').attr('disabled', 'true');
                        } else {
                            $('#titleInputError').css('display', 'none');
                        }
                    }
                });
            }
            if ($('#createdInput').val().match('\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}')) {
                $('#submitButton').attr('disabled', false);
            }
        }
    });

    $('#createdInput').blur(function(evt) {
        if (!$(this).val().match('\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}')) {
            $('#dateInputError').css('display', 'inline-block');
            $('#submitButton').attr('disabled', 'true');
        } else {
            $('#dateInputError').css('display', 'none');
            if ($('#titleInput').val().length > 0) {
                $('#submitButton').attr('disabled', false);
            }
        }
    }).keydown(function(event) {
        // catch up / down arrows to increase / decrease date field values
        if (event.keyCode == 38 || event.keyCode == 40) {
            var direction = event.keyCode == 38 ? 1 : -1;
            var date = new Date(event.srcElement.value);
            var position = event.srcElement.selectionStart;
            switch (position) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    date.setFullYear(date.getFullYear() + direction);
                    break;
                case 5:
                case 6:
                case 7:
                    date.setMonth(date.getMonth() + direction);
                    break;
                case 8:
                case 9:
                case 10:
                    date.setDate(date.getDate() + direction);
                    break;
                case 11:
                case 12:
                case 13:
                    date.setHours(date.getHours() + direction);
                    break;
                case 14:
                case 15:
                case 16:
                    date.setMinutes(date.getMinutes() + direction);
                    break;
            }
            event.srcElement.value = '' + date.getFullYear() + '/' +
                    (date.getMonth()+1 < 10 ? '0' + (date.getMonth()+1) : (date.getMonth()+1)) + '/' +
                    (date.getDate()    < 10 ? '0' +  date.getDate()     :  date.getDate())     + ' ' +
                    (date.getHours()   < 10 ? '0' +  date.getHours()    :  date.getHours())    + ':' +
                    (date.getMinutes() < 10 ? '0' +  date.getMinutes()  :  date.getMinutes());
            event.srcElement.selectionStart = position;
            event.srcElement.selectionEnd = position;
        }
        // allow left / right arrow to move to a different date field, as well as tab
        if (event.keyCode != 37 && event.keyCode != 39 && event.keyCode != 9) {
            event.preventDefault();
        }
    });

    $('#titleInput').focus();
});
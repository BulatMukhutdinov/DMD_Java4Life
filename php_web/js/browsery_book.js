;
(function(e, t, n) {
    function i(n, s) {
        if (!t[n]) {
            if (!e[n]) {
                var o = typeof require == "function" && require;
                if (!s && o) return o(n, !0);
                if (r) return r(n, !0);
                throw new Error("Cannot find module '" + n + "'")
            }
            var u = t[n] = {
                exports: {}
            };
            e[n][0].call(u.exports, function(t) {
                var r = e[n][1][t];
                return i(r ? r : t)
            }, u, u.exports)
        }
        return t[n].exports
    }
    var r = typeof require == "function" && require;
    for (var s = 0; s < n.length; s++) i(n[s]);
    return i
})({
    1: [function(require, module, exports) {
        // index.js

        var flip = flippant.flip
        var event = require('./event')

        document.addEventListener('click', function(e) {
            if (e.target.classList.contains('btnflip')) {
                e.preventDefault();
                var flipper = e.target.parentNode.parentNode
                var back

                var key = flipper.querySelector('.key').innerText;

                var title = '<p><label>Title</label><input type="text" id="title-input" value="' + flipper.querySelector('.title').innerText + '"></p>'
                var authors = flipper.querySelectorAll('.authors li')
                var authors_inputs = "";

                for (var i = 0; i < authors.length; i++) {
                    var n = i + 1
                    authors_inputs += '<p><label>Author #' + n + '</label><input type="text" class="authorsInput" id="author' + i + '" value="' + authors[i].innerText + '"></p>'
                }
                var addAuthor = '<a class="pure-button addAuthor" style="text-align: center" href="#"><i class="fa fa-plus"></i> Add author</a><br>'

                var editor = '<p><label>Editor</label><input type="text" id="editor-input" value="' + flipper.querySelector('.editor').innerText + '"></p>'

                var mdate = '<p><label>Mdate</label><input type="text" id="mdate-input" value="' + flipper.querySelector('.mdate').innerText + '"></p>'

                var pages = '<p><label>Pages</label><input type="text" id="pages-input" value="' + flipper.querySelector('.pages').innerText + '"></p>'

                var year = '<p><label>Year</label><input type="text" id="year-input" value="' + flipper.querySelector('.year').innerText + '"></p>'
                    
                var volume = '<p><label>Volume</label><input type="text" id="volume-input" value="' + flipper.querySelector('.volume').innerText + '"></p>'

                var month = '<p><label>Month</label><input type="text" id="month-input" value="' + flipper.querySelector('.month').innerText + '"></p>'

                var url = '<p><label>DBLP url</label><input type="text" id="url-input" value="' + flipper.querySelector('.url').innerText + '"></p>' //todo append href

                var ee = '<p><label>DOI url</label><input type="text" id="ee-input" value="' + flipper.querySelector('.ee').innerText + '"></p>' //todo append href

                var cdrom = '<p><label>CD-ROM</label><input type="text" id="cdrom-input" value="' + flipper.querySelector('.cdrom').innerText + '"></p>'

                var cite = '<p><label>Cite</label><input type="text" id="cite-input" value="' + flipper.querySelector('.cite').innerText + '"></p>'
                
                var publisher = '<p><label>Publisher</label><input type="text" id="publisher-input" value="' + flipper.querySelector('.publisher').innerText + '"></p>'

                var note = '<p><label>Note</label><input type="text" id="note-input" value="' + flipper.querySelector('.note').innerText + '"></p>'

                var isbn = '<p><label>ISBN</label><input type="text" id="isbn-input" value="' + flipper.querySelector('.isbn').innerText + '"></p>'
                
                var series = '<p><label>Series</label><input type="text" id="series-input" value="' + flipper.querySelector('.series').innerText + '"></p>'
                
                var school = '<p><label>School</label><input type="text" id="school-input" value="' + flipper.querySelector('.school').innerText + '"></p>'
                
                var chapter = '<p><label>Chapter</label><input type="text" id="chapter-input" value="' + flipper.querySelector('.chapter').innerText + '"></p>'
            
                if (e.target.classList.contains('btnflip')) {
                    back = flip(flipper, "<form class=\"pure-form pure-form-stacked\"><i class=\"fa fa-times fa-2x close-icon\"></i><h2>" + flipper.querySelector('.title').innerText + "</h2>" + key + title + '<div id="authorsInputs">' + authors_inputs + '</div>' + addAuthor + editor + mdate + pages + year + volume + month + url + ee + cdrom + cite + publisher + note + isbn + series + school + chapter + '</textarea><a class="delete pure-button button-warning" style="float: left;"><i class="fa fa-trash-o"></i> Delete</a><a class="update pure-button pure-button-primary" style="float: right;"><i class="fa fa-pencil"></i> Update</a></form>')
                } else {
                    //back = flip(flipper, "<p>It's a modal!</p>" + input + textarea, 'modal')
                }
                var n = authors.length;
                var temp = n + 1;

                back.addEventListener('click', function(e) {
                    if (e.target.classList.contains('btn')) {
                        //
                    } else if (e.target.classList.contains('close-icon')) {
                        event.trigger(back, 'close')
                    } else if (e.target.classList.contains('addAuthor')) {
                        var p = document.createElement('p');
                        p.className = "otherAuthor";
                        p.innerHTML = "<label>Author #" + temp + "</label><input type=\"text\" class=\"authorsInput\" id=\"author" + n + "\" value=\"\">";
                        e.target.previousSibling.appendChild(p, document.getElementById('authorsInputs'));
                        var otherAuthors = e.target.parentNode.querySelectorAll('.authorsInput');
                        for (var i = 0; i < otherAuthors.length; i++) {
                            console.log(otherAuthors[i].value);
                        }
                        n++;
                        temp = n + 1;
                    } else if (e.target.classList.contains('delete')) {
                        //console.log(flipper); //front
                        //console.log(e.target.parentNode); //the form
                        //console.log(back); //whole div (including form)
                        vex.dialog.open({
                            className: 'vex-theme-top',
                            message: '<div>Are you sure that you want to delete this record: <br>' + flipper.querySelector('.title').innerText + ' | ' + flipper.querySelector('.key').innerText + ' ?</div>',
                            buttons: [
                                $.extend({}, vex.dialog.buttons.YES, {
                                    text: 'Yes'
                                }),
                                $.extend({}, vex.dialog.buttons.NO, {
                                    text: 'No'
                                })
                            ],
                            onSubmit: function(ev) {
                                var $vexContent;
                                ev.preventDefault();
                                ev.stopPropagation();
                                $vexContent = $(this).parent();
                                $.ajax({
                                    type: "POST",
                                    url: 'book_delete.php',
                                    data: ({
                                        "key": key
                                    }),
                                    beforeSend: function() {
                                        document.querySelector('.vex-dialog-message').innerHTML = "Wait...";
                                    },
                                    success: function(data) {
                                        vex.close();
                                        vex.dialog.open({
                                            className: 'vex-theme-top',
                                            message: '<div>' + data + '</div>',
                                            buttons: [$.extend({}, vex.dialog.buttons.NO, {
                                                text: 'OK'
                                            })]
                                        });
                                        flipper.parentNode.removeChild(flipper);
                                        event.trigger(back, 'close')
                                    }
                                });
                            }
                        });

                    } else if (e.target.classList.contains('update')) {
                        var inputsChanged = {};
                        var otherAuthors = e.target.parentNode.querySelectorAll('.authorsInput');
                        for (var i = 0; i < otherAuthors.length; i++) {
                            inputsChanged['author' + i] = otherAuthors[i].value;
                        }
                        inputsChanged['key'] = key;
                        if ((e.target.parentNode.querySelector('#title-input').defaultValue) != (e.target.parentNode.querySelector('#title-input').value)) {
                            inputsChanged['title'] = e.target.parentNode.querySelector('#title-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#editor-input').defaultValue) != (e.target.parentNode.querySelector('#editor-input').value)) {
                            inputsChanged['editor'] = e.target.parentNode.querySelector('#editor-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#mdate-input').defaultValue) != (e.target.parentNode.querySelector('#mdate-input').value)) {
                            inputsChanged['mdate'] = e.target.parentNode.querySelector('#mdate-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#pages-input').defaultValue) != (e.target.parentNode.querySelector('#pages-input').value)) {
                            inputsChanged['pages'] = e.target.parentNode.querySelector('#pages-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#year-input').defaultValue) != (e.target.parentNode.querySelector('#year-input').value)) {
                            inputsChanged['year'] = e.target.parentNode.querySelector('#year-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#volume-input').defaultValue) != (e.target.parentNode.querySelector('#volume-input').value)) {
                            inputsChanged['volume'] = e.target.parentNode.querySelector('#volume-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#month-input').defaultValue) != (e.target.parentNode.querySelector('#month-input').value)) {
                            inputsChanged['month'] = e.target.parentNode.querySelector('#month-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#url-input').defaultValue) != (e.target.parentNode.querySelector('#url-input').value)) {
                            inputsChanged['url'] = e.target.parentNode.querySelector('#url-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#ee-input').defaultValue) != (e.target.parentNode.querySelector('#ee-input').value)) {
                            inputsChanged['ee'] = e.target.parentNode.querySelector('#ee-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#cdrom-input').defaultValue) != (e.target.parentNode.querySelector('#cdrom-input').value)) {
                            inputsChanged['cdrom'] = e.target.parentNode.querySelector('#cdrom-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#cite-input').defaultValue) != (e.target.parentNode.querySelector('#cite-input').value)) {
                            inputsChanged['cite'] = e.target.parentNode.querySelector('#cite-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#publisher-input').defaultValue) != (e.target.parentNode.querySelector('#publisher-input').value)) {
                            inputsChanged['publisher'] = e.target.parentNode.querySelector('#publisher-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#note-input').defaultValue) != (e.target.parentNode.querySelector('#note-input').value)) {
                            inputsChanged['note'] = e.target.parentNode.querySelector('#note-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#isbn-input').defaultValue) != (e.target.parentNode.querySelector('#isbn-input').value)) {
                            inputsChanged['isbn'] = e.target.parentNode.querySelector('#isbn-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#series-input').defaultValue) != (e.target.parentNode.querySelector('#series-input').value)) {
                            inputsChanged['series'] = e.target.parentNode.querySelector('#series-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#school-input').defaultValue) != (e.target.parentNode.querySelector('#school-input').value)) {
                            inputsChanged['school'] = e.target.parentNode.querySelector('#school-input').value;
                        };
                        if ((e.target.parentNode.querySelector('#chapter-input').defaultValue) != (e.target.parentNode.querySelector('#chapter-input').value)) {
                            inputsChanged['chapter'] = e.target.parentNode.querySelector('#chapter-input').value;
                        };
                        vex.dialog.open({
                            className: 'vex-theme-top',
                            message: '<div>Are you sure that you want to update this record: <br>' + flipper.querySelector('.title').innerText + ' | ' + key + ' ?</div>',
                            buttons: [
                                $.extend({}, vex.dialog.buttons.YES, {
                                    text: 'Yes'
                                }),
                                $.extend({}, vex.dialog.buttons.NO, {
                                    text: 'No'
                                })
                            ],
                            onSubmit: function(ev) {
                                var $vexContent;
                                ev.preventDefault();
                                ev.stopPropagation();
                                $vexContent = $(this).parent();
                                $.ajax({
                                    type: "POST",
                                    url: 'book_update.php',
                                    data: inputsChanged,
                                    beforeSend: function() {
                                        document.querySelector('.vex-dialog-message').innerHTML = "Wait...";
                                    },
                                    success: function(data) {
                                        vex.close();
                                        vex.dialog.open({
                                            className: 'vex-theme-top',
                                            message: '<div>' + data + '</div>',
                                            buttons: [$.extend({}, vex.dialog.buttons.NO, {
                                                text: 'OK'
                                            })]
                                        });
                                        event.trigger(back, 'close')
                                    }
                                });
                            }
                        });
                        flipper.querySelector('.title').innerText = back.querySelector('#title-input').value;
                        flipper.querySelector('.editor').innerText = back.querySelector('#editor-input').value
                        flipper.querySelector('.mdate').innerText = back.querySelector('#mdate-input').value
                        flipper.querySelector('.pages').innerText = back.querySelector('#pages-input').value
                        flipper.querySelector('.year').innerText = back.querySelector('#year-input').value

                        flipper.querySelector('.volume').innerText = back.querySelector('#volume-input').value
                        flipper.querySelector('.month').innerText = back.querySelector('#month-input').value
                        flipper.querySelector('.url').innerText = back.querySelector('#url-input').value
                        flipper.querySelector('.ee').innerText = back.querySelector('#ee-input').value
                        flipper.querySelector('.cdrom').innerText = back.querySelector('#cdrom-input').value
                        flipper.querySelector('.cite').innerText = back.querySelector('#cite-input').value
                        flipper.querySelector('.publisher').innerText = back.querySelector('#publisher-input').value
                        flipper.querySelector('.note').innerText = back.querySelector('#note-input').value
                        flipper.querySelector('.isbn').innerText = back.querySelector('#isbn-input').value
                        flipper.querySelector('.series').innerText = back.querySelector('#series-input').value
                        flipper.querySelector('.school').innerText = back.querySelector('#school-input').value
                        flipper.querySelector('.chapter').innerText = back.querySelector('#chapter-input').value
                    }

                })

            } else if (e.target.classList.contains('add_record')) {

                e.preventDefault();
                var flipper = document.querySelector('.new_record')
                var back

                var key = '<p><label>Key</label><input type="text" id="key-input" value="' + flipper.querySelector('.key').innerText + '"></p>'

                var title = '<p><label>Title</label><input type="text" id="title-input" value="' + flipper.querySelector('.title').innerText + '"></p>'
                var authors = flipper.querySelectorAll('.authors li')
                var authors_inputs = "";

                for (var i = 0; i < authors.length; i++) {
                    var n = i + 1
                    authors_inputs += '<p><label>Author #' + n + '</label><input type="text" class="authorsInput" id="author' + i + '" value="' + authors[i].innerText + '"></p>'
                }
                var addAuthor = '<a class="pure-button addAuthor" style="text-align: center" href="#"><i class="fa fa-plus"></i> Add author</a><br>'

                var editor = '<p><label>Editor</label><input type="text" id="editor-input" value="' + flipper.querySelector('.editor').innerText + '"></p>'

                var mdate = '<p><label>Mdate</label><input type="text" id="mdate-input" value="' + flipper.querySelector('.mdate').innerText + '"></p>'

                var pages = '<p><label>Pages</label><input type="text" id="pages-input" value="' + flipper.querySelector('.pages').innerText + '"></p>'

                var year = '<p><label>Year</label><input type="text" id="year-input" value="' + flipper.querySelector('.year').innerText + '"></p>'
                                
                var volume = '<p><label>Volume</label><input type="text" id="volume-input" value="' + flipper.querySelector('.volume').innerText + '"></p>'

                var month = '<p><label>Month</label><input type="text" id="month-input" value="' + flipper.querySelector('.month').innerText + '"></p>'

                var url = '<p><label>DBLP url</label><input type="text" id="url-input" value="' + flipper.querySelector('.url').innerText + '"></p>' //todo append href

                var ee = '<p><label>DOI url</label><input type="text" id="ee-input" value="' + flipper.querySelector('.ee').innerText + '"></p>' //todo append href

                var cdrom = '<p><label>CD-ROM</label><input type="text" id="cdrom-input" value="' + flipper.querySelector('.cdrom').innerText + '"></p>'

                var cite = '<p><label>Cite</label><input type="text" id="cite-input" value="' + flipper.querySelector('.cite').innerText + '"></p>'
                
                var publisher = '<p><label>Publisher</label><input type="text" id="publisher-input" value="' + flipper.querySelector('.publisher').innerText + '"></p>'

                var note = '<p><label>Note</label><input type="text" id="note-input" value="' + flipper.querySelector('.note').innerText + '"></p>'

                var isbn = '<p><label>ISBN</label><input type="text" id="isbn-input" value="' + flipper.querySelector('.isbn').innerText + '"></p>' 
                
                var series = '<p><label>Series</label><input type="text" id="series-input" value="' + flipper.querySelector('.series').innerText + '"></p>' 
                
                var school = '<p><label>School</label><input type="text" id="school-input" value="' + flipper.querySelector('.school').innerText + '"></p>' 
                
                var chapter = '<p><label>Chapter</label><input type="text" id="chapter-input" value="' + flipper.querySelector('.chapter').innerText + '"></p>' 
                
                back = flip(flipper, "<form class=\"pure-form pure-form-stacked\"><i class=\"fa fa-times fa-2x close-icon\"></i><h2>" + flipper.querySelector('.title').innerText + "</h2>" + key + title + '<div id="authorsInputs">' + authors_inputs + '</div>' + addAuthor + editor + mdate + pages + year + volume + month + url + ee + cdrom + cite + publisher + note + isbn + series + school + chapter + '</textarea><a class="addButtonBottom pure-button pure-button-primary" style="float: right;"><i class="fa fa-pencil"></i> Insert</a></form>', 'modal');
                var n = authors.length;
                var temp = n + 1;

                back.addEventListener('click', function(e) {
                    if (e.target.classList.contains('close-icon')) {
                        event.trigger(back, 'close')
                    } else if (e.target.classList.contains('addAuthor')) {
                        var p = document.createElement('p');
                        p.className = "otherAuthor";
                        p.innerHTML = "<label>Author #" + temp + "</label><input type=\"text\" class=\"authorsInput\" id=\"author" + n + "\" value=\"\">";
                        e.target.previousSibling.appendChild(p, document.getElementById('authorsInputs'));
                        var otherAuthors = e.target.parentNode.querySelectorAll('.authorsInput');
                        for (var i = 0; i < otherAuthors.length; i++) {
                            console.log(otherAuthors[i].value);
                        }
                        n++;
                        temp = n + 1;
                    } else if (e.target.classList.contains('addButtonBottom')) {
                        console.log(e.target.parentNode);
                        var inputsChanged = {};
                        var otherAuthors = e.target.parentNode.querySelectorAll('.authorsInput');
                        for (var i = 0; i < otherAuthors.length; i++) {
                            inputsChanged['author' + i] = otherAuthors[i].value;
                        }
                        inputsChanged['key'] = e.target.parentNode.querySelector('#key-input').value;
                        inputsChanged['title'] = e.target.parentNode.querySelector('#title-input').value;
                        inputsChanged['editor'] = e.target.parentNode.querySelector('#editor-input').value;
                        inputsChanged['mdate'] = e.target.parentNode.querySelector('#mdate-input').value;
                        inputsChanged['pages'] = e.target.parentNode.querySelector('#pages-input').value;
                        inputsChanged['year'] = e.target.parentNode.querySelector('#year-input').value;
                        inputsChanged['volume'] = e.target.parentNode.querySelector('#volume-input').value;
                        inputsChanged['month'] = e.target.parentNode.querySelector('#month-input').value;
                        inputsChanged['url'] = e.target.parentNode.querySelector('#url-input').value;
                        inputsChanged['ee'] = e.target.parentNode.querySelector('#ee-input').value;
                        inputsChanged['cdrom'] = e.target.parentNode.querySelector('#cdrom-input').value;
                        inputsChanged['cite'] = e.target.parentNode.querySelector('#cite-input').value;
                        inputsChanged['publisher'] = e.target.parentNode.querySelector('#publisher-input').value;
                        inputsChanged['note'] = e.target.parentNode.querySelector('#note-input').value;
                        inputsChanged['isbn'] = e.target.parentNode.querySelector('#isbn-input').value;
                        inputsChanged['series'] = e.target.parentNode.querySelector('#series-input').value;
                        inputsChanged['school'] = e.target.parentNode.querySelector('#school-input').value;
                        inputsChanged['chapter'] = e.target.parentNode.querySelector('#chapter-input').value;
                        console.log(inputsChanged);
                        vex.dialog.open({
                            className: 'vex-theme-top',
                            message: '<div>Are you sure that you want to add this record: <br>' + inputsChanged['title'] + ' | ' + inputsChanged['key'] + ' ?</div>',
                            buttons: [
                                $.extend({}, vex.dialog.buttons.YES, {
                                    text: 'Yes'
                                }),
                                $.extend({}, vex.dialog.buttons.NO, {
                                    text: 'No'
                                })
                            ],
                            onSubmit: function(ev) {
                                var $vexContent;
                                ev.preventDefault();
                                ev.stopPropagation();
                                $vexContent = $(this).parent();
                                $.ajax({
                                    type: "POST",
                                    url: 'book_insert.php',
                                    data: inputsChanged,
                                    beforeSend: function() {
                                        document.querySelector('.vex-dialog-message').innerHTML = "Wait...";
                                    },
                                    success: function(data) {
                                        vex.close();
                                        vex.dialog.open({
                                            className: 'vex-theme-top',
                                            message: '<div>' + data + '</div>',
                                            buttons: [$.extend({}, vex.dialog.buttons.NO, {
                                                text: 'OK'
                                            })]
                                        });
                                        event.trigger(back, 'close')
                                    }
                                });
                            }
                        });

                    }

                })



            }
        })


    }, {
        "./event": 2
    }],
    2: [function(require, module, exports) {
        exports.trigger = function(elm, event_name, data) {
            var evt = new CustomEvent(event_name, data)
            elm.dispatchEvent(evt)
        }
    }, {}]
}, {}, [1]);
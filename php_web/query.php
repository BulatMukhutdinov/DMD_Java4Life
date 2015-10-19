<!DOCTYPE html>
<html>
	<head>
		<?php 
			include('header.inc.php');
		?>
		<!-- Create a simple CodeMirror instance -->
	<link rel="stylesheet" href="css/codemirror.css">
	<script src="js/codemirror.js"></script>
	<script src="js/sql.js"></script>
	<link rel="stylesheet" href="css/show-hint.css" />
	<script src="js/show-hint.js"></script>
	<script src="js/sql-hint.js"></script>
	</head>
	<body>
	<?php 
			include('topnav.inc.php');
	?>
	<div class="pure-g" id="content_wrapper">
		<div id="menu" class="pure-u-1-8">
			<div class="pure-menu">
				<a class="pure-menu-heading" href="#">TABLES</a>

				<ul class="pure-menu-list">
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Articles</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Books</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Incollections</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Inproceedings</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Masters Thesises</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">PHD Thesises</a></li>
					<li class="pure-menu-item"><a href="#" class="pure-menu-link">Proceedings</a></li>
				</ul>
			</div>
		</div>
		<div id="content" class="pure-u-7-8">
				<form class="pure-form queryForm">
					<textarea id="queryCode" name="queryCode">SELECT * FROM database;</textarea>
					<button type="submit" class="pure-button pure-button-primary">Submit</button>
				</form>
		</div>
	</div>
		<script>
		window.onload = function() {
		  var mime = 'text/x-sql';
		  window.editor = CodeMirror.fromTextArea(document.getElementById('queryCode'), {
			mode: mime,
			indentWithTabs: true,
			smartIndent: true,
			lineNumbers: true,
			matchBrackets : true,
			autofocus: true,
			extraKeys: {"Ctrl-Space": "autocomplete"},
			hintOptions: {tables: {
			  users: {name: null, score: null, birthDate: null},
			  countries: {name: null, population: null, size: null}
			}}
		  });
		};
		</script>
		<script>
	$('a.top-login').click(function(event){
    event.preventDefault();
    vex.dialog.open({
	  message: 'Please enter your username and password:',
	  input: "<input name=\"username\" type=\"text\" placeholder=\"Username\" required />\n<input name=\"password\" type=\"password\" placeholder=\"Password\" required />",
	  buttons: [
		$.extend({}, vex.dialog.buttons.YES, {
		  text: 'Login'
		}), $.extend({}, vex.dialog.buttons.NO, {
		  text: 'Back'
		}),
		$.extend({}, vex.dialog.buttons.NO, { text: 'Register', click: function($vexContent, event) {
			vex.close();
            vex.dialog.open({ 
							message: 'Please, enter your credentials to create account',
							input: '<input name=\"username\" type=\"text\" placeholder=\"Username\" required />\n<input name=\"email\" type=\"email\" placeholder=\"Email\"/>\n<input name=\"password\" type=\"password\" placeholder=\"Password\" required />\n<input name=\"password_conf\" type=\"password\" placeholder=\"Password confirm\" required />',
							buttons: [$.extend({}, vex.dialog.buttons.YES, {
								  text: 'Register'
								})],
							onSubmit: function(event) {
								var $vexContent2;
								event.preventDefault();
								event.stopPropagation();
								$vexContent2 = $(this).parent();
								  $.ajax({
									   type: "POST",
									   url: 'register.php',
									   data: $(this).serialize(),
									   success: function(data)
									   {
										  var $username = document.getElementsByName("username")[0].value;
										  if (data == "Success!") {
											vex.dialog.open({
												className: 'vex-theme-top', 
												message: '<div>Welcome to our website, ' + $username + '</div>',
												buttons: []	// sets a primary content
											});
											$("body").delay(1000).animate({ opacity: 0, backgroundColor: '#000' }, function() {
											  window.location = '/index.html'
											})
										  }
										  else if (data ==="You cannot use this login to register"){
											vex.dialog.open({
												className: 'vex-theme-top', 
												message: '<div>Invalid credentials: ' + $username + '</div>',
												buttons: [$.extend({}, vex.dialog.buttons.YES, {text: 'OK'})]
											});
										  }
										  else if (data ==="Password and its confirmation should match!"){
											vex.dialog.open({
												className: 'vex-theme-top', 
												message: '<div>Password and confirmation should match</div>',
												buttons: [$.extend({}, vex.dialog.buttons.YES, {text: 'OK'})]
											});
										  }
									   }
								   });	
							}
															
							});
								}})
				],
	  onSubmit: function(event) {
            var $vexContent;
            event.preventDefault();
            event.stopPropagation();
            $vexContent = $(this).parent();
			  $.ajax({
				   type: "POST",
				   url: 'login.php',
				   data: $(this).serialize(),
				   success: function(data)
				   {
					  var $username = document.getElementsByName("username")[0].value;
					  if (data == "true") {
						vex.dialog.open({
							className: 'vex-theme-top', 
							message: '<div>Hello, ' + $username + '</div>',
							buttons: []	// sets a primary content
						});
						$("body").delay(1000).animate({ opacity: 0, backgroundColor: '#000' }, function() {
						  window.location = '/user-page.php'
						})
					  }
					  else {
						vex.dialog.open({
							className: 'vex-theme-top', 
							message: '<div>Invalid credentials: ' + $username + '</div>',
							buttons: [$.extend({}, vex.dialog.buttons.YES, {text: 'OK'})]
						});
					  }
				   }
			   });
			  
        }
	});	
	});
	</script>
	</body>
</html>
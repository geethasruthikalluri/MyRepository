<!DOCTYPE>
<html>
<head>
<title>CRM</title>
<!-- Include one of jTable styles. -->
<link href="css/metro/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<!-- Include jTable script file. -->
<script src="js/jquery-1.8.2.js" type="text/javascript"></script>
<script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
<script src="js/jquery.jtable.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#StudentTableContainer').jtable({
			title : 'Contacts',
			actions : {
				listAction : 'Controller?action=list',
				createAction : 'Controller?action=create',
				updateAction : 'Controller?action=update',
				deleteAction : 'Controller?action=delete'
			},
			fields : {
				Id : {
					title : 'Id',
					width : '10%',
					key : true,
					list : true,
					edit : false,
					create : false
				},
				firstName : {
					title : 'FirstName',
					width : '25%',
					edit : true,
					
				},
				lastName : {
					title : 'LastName',
					width : '25%',
					edit : true
				},
				address1 : {
					title : 'address1',
					width : '25%',
					edit : true
				},
				address2 : {
					title : 'address2',
					width : '20%',
					edit : true
				},
				city : {
					title : 'City',
					width : '20%',
					edit : true
				},
				 postalCode: {
					title : 'postalcode',
					width : '10%',
					edit : true
				},
				mobile : {
					title : 'mobile',
					width : '30%',
					edit : true
				},
				email : {
					title : 'email',
					width : '30%',
					edit : true
				}
			}
		});
		$('#StudentTableContainer').jtable('load');
	});
	
	$(document).ready(function() {
		$('#ActivityTableContainer').jtable({
			title : 'Activities',
			actions : {
				listAction : 'Controller?action=list',
				createAction : 'Controller?action=create',
				updateAction : 'Controller?action=update',
				deleteAction : 'Controller?action=delete'
			},
			fields : {
				Id : {
					title : 'Id',
					width : '10%',
					key : true,
					list : true,
					edit : false,
					create : false
				},
				firstName : {
					title : 'FirstName',
					width : '25%',
					edit : true,
					
				},
				lastName : {
					title : 'LastName',
					width : '25%',
					edit : true
				},
				address1 : {
					title : 'address1',
					width : '25%',
					edit : true
				},
				address2 : {
					title : 'address2',
					width : '20%',
					edit : true
				},
				city : {
					title : 'City',
					width : '20%',
					edit : true
				},
				 postalCode: {
					title : 'postalcode',
					width : '10%',
					edit : true
				},
				mobile : {
					title : 'mobile',
					width : '30%',
					edit : true
				},
				email : {
					title : 'email',
					width : '30%',
					edit : true
				}
			}
		});
		$('#StudentTableContainer').jtable('load');
	});
</script>

</head>
<body>
<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>CONTACTS</h4>
		<div id="StudentTableContainer"></div>
	</div>
	<div style="width: 80%; margin-right: 10%; margin-left: 10%; text-align: center;">

		<h4>ACTIVITIES</h4>
		<div id="ActivityTableContainer"></div>
	</div>
</body>
</html>
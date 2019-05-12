<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>

<body>

	<h2>New Shop</h2>
	<form>
			name : <input type="text" id="txtName" /> <br /> 
			qtyOnHand : <input type="number" id="txtQtyOnHand" /> <br /> 
			unitPrice : <input type="number" id="txtUnitPrice" /> <br /> 
			<input type="button" onclick="addItem()" value="Add item" />
	</form>
	<br><br>
	
	<input type="button" onclick="viewItems()" value="view items" />
	
	<br><br>
	
	<TABLE id="dataTable" width="350px" border="1">
	
		<thead>
             <tr>
             			<th>item id</th>
                        <th>item name</th>
                        <th>quantity</th>
                        <th>unit price</th>
                        <th>Edit</th>
                        <th>Delete</th>
             </tr>
        </thead>
        <tbody id = "tableBody">
        </tbody>
	</TABLE>

    <br><br>

	<script type="text/javascript">
	
		
		function deleteItem(){
			//document.getElementById("demo").innerHTML = "Hello World";
			alert("you delete the value! " + this.itemid);
			var request = getRequest();
		}
		
		function updateItem(){
			alert("you edit the value! " + this.itemid);
		}
		
	
		function viewItems() {
			var request = getRequest();
			request.onload = function() {
				
				var itemsString = request.responseText;
			
				var items = JSON.parse(itemsString);
				
				var table = document.getElementById("tableBody");
				
				//table.rows.length = 0;
				/*for(i=0; i<table.rows.length; i++ ){
					table.deleteRow(0);
				}*/
				
				table.innerHTML = "";  
				
				alert(table.rows.length);
				for(i=0; i<items.length; i++){
					var item = items[i];
					var rowCount = table.rows.length;
					var row = table.insertRow(rowCount);
					
					var cell0 = row.insertCell(0);
					var element0 = document.createElement("input");
					element0.type = "text"; 
					element0.readOnly = true;
					element0.name = "itemid" + (i+1) ;
					element0.value = item.id;
					cell0.appendChild(element0);
					
					var cell1 = row.insertCell(1);
					var element1 = document.createElement("input");
					element1.type = "text"; 
					element1.readOnly = true;
					element1.name = "itemName" + (i+1) ;
					element1.value = item.name;
					cell1.appendChild(element1);
					
					var cell2 = row.insertCell(2);
					var element2 = document.createElement("input");
					element2.type = "text"; 
					element2.readOnly = true;
					element2.name = "quantity" + (i+1) ;
					element2.value = item.qtyOnHand;
					cell2.appendChild(element2);
					
					var cell3 = row.insertCell(3);
					var element3 = document.createElement("input");
					element3.type = "text"; 
					element3.readOnly = true;
					element3.name = "unitPrice" + (i+1) ;
					element3.value = item.unitPrice;
					cell3.appendChild(element3);
					
					var cell4 = row.insertCell(4); 
					var element4 = document.createElement("button");
					element4.type = "submit";
					element4.innerHTML = "Edit";
					element4.name = "editBtn" + (i+1);
					element4.itemid = item.id;
					element4.addEventListener("click", updateItem);
					/*element4.addEventListener("click", function(){
						alert("you edit the value!");
						  //document.getElementById("demo").innerHTML = "Hello World";
					});*/
					cell4.appendChild(element4);
					
					var cell5 = row.insertCell(5);
					var element5 = document.createElement("button");
					element5.type = "submit";
					element5.innerHTML = "Delete";
					element5.name = "deleteBtn" + (i+1);
					element5.itemid = item.id;
					element5.addEventListener("click", deleteItem);
					
					/*element5.addEventListener("click", function(){
						alert(this.itemid);  
					});*/
					cell5.appendChild(element5);	
				}
				
				/*document.getElementById("d").innerHTML = "";
				  for(i=0;i<items.length;i++){
					  var item = items[i];
					  document.getElementById("d").innerHTML += item.name + ", " + item.qtyOnHand + ", " + item.unitPrice + "<br/>";
				  }*/
			};
			request.open("GET", "GetItems", true);
			request.send(null);
		}
		
		function addItem() {

			var name = document.getElementById("txtName").value;
			var qtyOnHand = document.getElementById("txtQtyOnHand").value;
			var unitPrice = document.getElementById("txtUnitPrice").value;

			var request = getRequest();
			request.onload = function() {
				alert(request.responseText);

			};
			request.open("GET", "AddItem?name=" + name + "&qtyOnHand="
					+ qtyOnHand + "&unitPrice=" + unitPrice, true);
			request.send(null);
		}

		function getRequest() {
			var xmlHttp;
			try {
				// Firefox, Opera 8.0+, Safari
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				// Internet Explorer
				try {
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}
			return xmlHttp;
		}
	</script>
</body>


</html>
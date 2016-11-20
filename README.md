# Skateboarding Trick List
Skateboarding Trick List is Java Web App built with Maven and Spring MVC. The frontend is built with Bootstrap.

# Overview
<img src="/images/skatetrick1.png" width="600">  
On this page you can add, edit, delete, or get more information about a skateboarding trick.


<img src="/images/skatetrick2.png" width="600">  
Clicking on a trick name will display this modal with more information. The original click triggers an AJAX request to a REST endpoint.


<img src="/images/skatetrick3.png" width="600">  
Clicking the edit button displays this modal with information filled in via an AJAX request to a REST endpoint. Clicking the Edit Trick
button will send an AJAX PUT request to a REST endpoint updating an in-memory HashMap.


<img src="/images/skatetrick4.png" width="600">  
An AJAX request retrives the matching values from the HashMap then lists them in a table.


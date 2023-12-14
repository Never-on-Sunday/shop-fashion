<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Conditional Modal Example</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

    <!-- Bootstrap JS and jQuery (make sure to include them before your custom script) -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%
    // Your condition (replace this with your actual condition)
    boolean showModal = false;
%>

<!-- Bootstrap Modal -->
<div id="myModal" class="modal fade <%=(showModal ? "show" : "") %>" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="<%=(!showModal ? "true" : "") %>">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Conditional Modal</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>This is the modal content.</p>
            </div>
        </div>
    </div>
</div>

<!-- JavaScript to show the modal -->
<script>
    // Show the modal when the page is loaded if the condition is true
    $(document).ready(function() {
        <% if (showModal) { %>
            $('#myModal').modal('show');
        <% } %>
    });
</script>

</body>
</html>

<%-- 
    Document   : detailsEditModalFragment
    Created on : Oct 31, 2016, 10:17:33 PM
    Author     : Jimmy Cook 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="modal fade" id="detailsModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Trick Details</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="trick-id"></h3>
                        <table class="table table-bordered">
                            <tr>
                                <th>Name:</th>
                                <td id="trick-name"></td>
                            </tr>
                            <tr>
                                <th>Category:</th>
                                <td id="trick-category"></td>
                            </tr>
                            <tr>
                                <th>Type:</th>
                                <td id="trick-type"></td>
                            </tr>
                            <tr>
                                <th>Difficulty Rating:</th>
                                <td id="trick-rating"></td>
                            </tr>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Edit Modal -->
        <div class="modal fade" id="editModal" tabindex="-1" role="dialog"
             aria-labelledby="detailsModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">
                            <span aria-hidden="true">&times;</span>
                            <span class="sr-only">Close</span>
                        </button>
                        <h4 class="modal-title" id="detailsModalLabel">Edit Trick</h4>
                    </div>
                    <div class="modal-body">
                        <h3 id="trick-id"></h3>
                        <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <label for="edit-name" class="col-md-4 control-label">
                                    Trick Name:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-name"
                                           placeholder="Trick Name">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-category" class="col-md-4 control-label">
                                    Category:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-category"
                                           placeholder="Category">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-type" class="col-md-4 control-label">
                                    Type:
                                </label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="edit-type"
                                           placeholder="Type">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="edit-rating" class="col-md-4 control-label">
                                    Difficulty Rating:
                                </label>
                                <div class="col-md-8">
                                    <input type="number" class="form-control" id="edit-rating"
                                           placeholder="Rating">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <button type="submit" id="edit-button" class="btn btn-default"
                                            data-dismiss="modal">
                                        Edit Trick
                                    </button>
                                    <button type="button" class="btn btn-default"
                                            data-dismiss="modal">
                                        Cancel
                                    </button>
                                    <input type="hidden" id="edit-trick-id">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

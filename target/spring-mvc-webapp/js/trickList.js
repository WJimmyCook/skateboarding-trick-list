/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// Document ready function
$(document).ready(function () {
    loadTricks();

    // Add new Trick via Ajax
    $('#add-button').click(function (event) {
        event.preventDefault();
        $.ajax({
            type: 'POST',
            url: 'trick',
            data: JSON.stringify({
                name: $('#add-name').val(),
                category: $('#add-category').val(),
                type: $('#add-type').val(),
                rating: $('#add-rating').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function (data, status) {
            // If call is successful, clear form and reload summary trick table
            $('#validationErrors').hide();
            $('#add-name').val('');
            $('#add-category').val('');
            $('#add-type').val('');
            $('#add-rating').val('');
            loadTricks();
        }).error(function (data, status) {
            var errorDiv = $('#validationErrors');
            errorDiv.empty();
            errorDiv.show();
            $.each(data.responseJSON.fieldErrors, function (index, validationError) {
                errorDiv.append(validationError.message);
                errorDiv.append('<br>');
            });
        });
    });
// edit trick via Ajax
    $('#edit-button').click(function (event) {
        // prevent button press from submitting the whole page
        event.preventDefault();
        var trickId = $('#edit-trick-id').val();

        $.ajax({
            type: 'PUT',
            url: 'trick/' + trickId,
            data: JSON.stringify({
                id: $('#edit-trick-id').val(),
                name: $('#edit-name').val(),
                category: $('#edit-category').val(),
                type: $('#edit-type').val(),
                rating: $('#edit-rating').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json'
        }).success(function () {
            loadTricks();
        });
    });

    $('#search-button').click(function (e) {
        e.preventDefault();
         searchTricks();
    });
// This code runs in response to the show.bs.modal event - it gets the correct
// contact data and renders it to the dialog
    $('#detailsModal').on('show.bs.modal', function (event) {
        // Get the element that triggered this event 
        var element = $(event.relatedTarget);
        var trickId = element.data('trick-id');
        var modal = $(this);
        // make an ajax call to get trick information for id
        $.ajax({
            type: 'GET',
            url: 'trick/' + trickId
        }).success(function (trick) {
            modal.find('#trick-id').text(trick.id);
            modal.find('#trick-name').text(trick.name);
            modal.find('#trick-category').text(trick.category);
            modal.find('#trick-type').text(trick.type);
            modal.find('#trick-rating').text(trick.rating);
        });
    });

    $('#editModal').on('show.bs.modal', function (event) {

        var element = $(event.relatedTarget);
        var trickId = element.data('trick-id');

        var modal = $(this);

        $.ajax({
            type: 'GET',
            url: 'trick/' + trickId
        }).success(function (trick) {
            modal.find('#trick-id').text(trick.id);
            modal.find('#edit-trick-id').val(trick.id);
            modal.find('#edit-name').val(trick.name);
            modal.find('#edit-category').val(trick.category);
            modal.find('#edit-type').val(trick.type);
            modal.find('#edit-rating').val(trick.rating);
        });
    });



});

//==========
// FUNCTIONS
//==========
// Load tricks into the summary table
function loadTricks() {
    // Clear the previous list
    clearTrickTable();
    // Make an Ajax GET call to the 'tricks' endpoint. Iterate through all
    // JSON objects and render them to the summary table
    $.ajax({
        url: 'tricks'
    }).success(function (data, status) {
        fillTrickTable(data, status);
    });
}

// Clear all content rows from the summary table
function clearTrickTable() {
    $('#contentRows').empty();
}

// function to delete a trick
function deleteTrick(id) {
    var answer = confirm("Do you really want to delete this trick?");

    if (answer === true) {
        $.ajax({
            type: 'DELETE',
            url: 'trick/' + id
        }).success(function () {
            loadTricks();
        });
    }
}

function searchTricks() {
    $.ajax({
        type: 'POST',
        url: 'search/tricks',
        data: JSON.stringify({
            name: $('#search-name').val(),
            category: $('#search-category').val(),
            type: $('#search-type').val(),
            rating: $('#search-rating').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#search-name').val('');
        $('#search-category').val('');
        $('#search-type').val('');
        $('#search-rating').val('');

        fillTrickTable(data, status);
    });
}

function fillTrickTable(trickList, status) {

    clearTrickTable();

    var tTable = $('#contentRows');

    $.each(trickList, function (index, trick) {
        tTable.append($('<tr>')
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-trick-id': trick.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#detailsModal'
                                })
                                .text(trick.name)
                                )
                        )
                .append($('<td>').text(trick.category))
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'data-trick-id': trick.id,
                                    'data-toggle': 'modal',
                                    'data-target': '#editModal'
                                })
                                .text('Edit')
                                ) // ends the <a> tag
                        ) // ends the <td> tag for Edit
                .append($('<td>')
                        .append($('<a>')
                                .attr({
                                    'onClick': 'deleteTrick(' + trick.id + ')'
                                })
                                .text('Delete')
                                ) // ends the <a> tag
                        ) // end <td> tag
                );
    });
}


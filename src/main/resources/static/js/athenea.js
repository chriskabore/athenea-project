// Build action plan tree
function buildActionPlanTree(tree){

    // build tree with tree Nodes
    $('#action-plan-tree').tree({
        data: tree,
        dragAndDrop: true,
        autoEscape: false,
        autoOpen: false,
        onCreateLi: function(node, $li){

            <!-- Call to action buttons -->

            $li.addClass('d-inline-block');

            $li.find('div.jqtree-element').each(function () {
                $(this).addClass('border rounded my-3');
            });



            $li.find('.jqtree-element').append(
                '<div class="action-plan-element-controls float-right d-inline">'+
                '<a class="btn btn-sm rounded view-control mx-1" href="#" data-toggle="tooltip" data-placement="top" title="View">'+
                '<i class="fa fa-eye"></i>'+
                '</a>'+
                '<a class="btn btn-sm rounded edit-control mx-1" href="#" data-toggle="tooltip" data-placement="top" title="Edit">'+
                '<i class="fa fa-edit"></i>'+
                '</a>'+
                '<a class="btn btn-sm rounded delete-control mx-1" href="#" data-toggle="tooltip" data-placement="top" title="Delete">'+
                '<i class="fa fa-trash"></i>'+
                '</a>'+
                '</div>'
            );
        }
    });
}



iNet.ns("iNet.resources.grid","iNet.resources.admin.knobstick");
if (iNet.resources.grid) {
    iNet.apply(iNet.resources.grid, {
        beforePageText: 'Page',
        afterPageText: 'of {0}',
        firstText: 'First page',
        prevText: 'Previous page',
        nextText: 'Next page',
        lastText: 'Last page',
        displayMsg: 'Displaying <b>{0}</b> - <b>{1}</b> of <b>{2}</b>',
        emptyMsg: 'No data to display',
        refreshText: 'Refresh',
        searchText: 'Search',
        searchPlaceholder: 'Keyword',
        downloadText: 'Download'
    });
}
if (iNet.resources.ajaxLoading) {
    iNet.apply(iNet.resources.ajaxLoading, {
        loading: 'Loading data ...',
        saving: 'Saving data ...',
        deleting: 'Deleting data ...'
    });
}
if (iNet.resources.admin.knobstick) {
  iNet.apply(iNet.resources.admin.knobstick, {
    notation_field: 'Notation',
    title_field: 'Title',
    type_field: 'Type',
    type_edoc: 'Electronic document',
    agency_empty: '<div class="red">Your account is not linked with a agency.</div>',
    sender_field: 'Sender',
    receiver_field: 'Receiver',
    status_field: 'Status',
    status_initial: 'Initial',
    status_processing: 'Processing',
    status_fail: 'Fail',
    status_done: 'Done',
    create_date_field: 'Sent date',
    completed_date_field: 'Completed date',
    at_time: 'at',
    agency_code_field: 'Agency code',
    agency_name_field: 'Agency name',
    number_of_sent_field: 'Sent',
    number_of_received_field: 'Received',
    number_of_received_success: 'Success',
    number_of_received_failure: 'Failure',
    number_of_not_received: 'Not received',
    confirm_delete_content: 'Are you sure you want to delete the selected items?' ,
    confirm_clear_title: 'Erase ?',
    confirm_clear_content: 'Are you sure you want to erase the items?',
    delete_title: 'Delete knobsticks information',
    delete_error: 'An error occrus during deleting knobsticks information.',
    clear_title: 'Clear matched knobsticks information',
    clear_error: 'An error ocurs during clearing matched knobsticks information.',
    send_dlg_title: 'Send of',
    receive_dlg_title: 'Receive of',
    receive: 'RECEIVE'
  });
}
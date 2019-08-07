import React from 'react'
import columns from './ColumnsConfig'

import ReactTable from 'react-table'

const Presentation = (props) => {
    return (
        <div className="">
            <div className="col">
                <ReactTable
                    {...props}
                    manual
                    data={props.data}
                    pages={props.pages}
                    loading={props.data_loading}
                    onFetchData={props.fetchData}
                    onPageSizeChange={props.onPageSizeChange}
                    columns={columns}
                    defaultPageSize={props.defaultPageSize}
                    className="-striped -highlight" />
            </div>
        </div>)
}

export default Presentation;
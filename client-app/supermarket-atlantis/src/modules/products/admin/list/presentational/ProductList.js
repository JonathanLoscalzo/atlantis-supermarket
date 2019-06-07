import React from 'react'
import columns from './ColumnsConfig'
import ReactTable from 'react-table'

const ProductList = (props) => {
    return (
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
    )
}

export default ProductList;
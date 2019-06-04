import React from 'react'
import columns from './ColumnsConfig'
import ReactTable from 'react-table'

const ProductList = (props) => {
    return (
        <div>
            <ReactTable
                data={props.products}
                columns={columns}
                defaultPageSize={10}
                className="-striped -highlight" />
        </div>
    )
}

export default ProductList;
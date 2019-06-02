import React from 'react'
import ReactTable from 'react-table'
import columns from './ConfigColumns'

const Presentation = (props) => {
    return (
        <ReactTable 
            data={props.clients}
            columns={columns}
            defaultPageSize={10}
            className="-striped -highlight" />
    )
}

export default Presentation
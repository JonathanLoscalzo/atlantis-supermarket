import * as yup from 'yup'
import { YupConfig } from '../../../../shared/index'
import batchTypes from '../../../shared/batchTypes'

const schema = (mode = "CREATE") => {
    if (mode == "CREATE") {
        return yup
            .object()
            .shape({
                id: yup.string(),
                productId: yup.string(),
                detail: yup.string().required(),
                type:yup.mixed().oneOf(["DEFAULT", "EXPIRATION"]),
                expiration: yup.date().when("type", { is: 'EXPIRATION', then: s => s.required() }),
                remainingUnits: yup.number().positive().required()
            })
    }
}

export default schema;













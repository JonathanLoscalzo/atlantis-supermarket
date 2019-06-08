import * as yup from 'yup'
import { YupConfig } from '../../../../shared/index'

const schema = yup
    .object()
    .shape({
        id: yup.string(),
        description: yup.string().required()
    })

export default schema;
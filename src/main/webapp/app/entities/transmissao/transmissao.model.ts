import { BaseEntity } from './../../shared';

export class Transmissao implements BaseEntity {
    constructor(
        public id?: number,
        public timeStamp?: any,
        public versaoColetor?: string,
        public inventario?: BaseEntity,
        public funcionario?: BaseEntity,
        public coletor?: BaseEntity,
        public tipoContagem?: BaseEntity,
    ) {
    }
}

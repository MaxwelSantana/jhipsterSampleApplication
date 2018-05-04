import { BaseEntity } from './../../shared';

export class Transmissao implements BaseEntity {
    constructor(
        public id?: number,
        public timeStamp?: any,
        public versaoColetor?: string,
        public funcionario?: BaseEntity,
        public coletor?: BaseEntity,
        public tipoContagem?: BaseEntity,
        public inventario?: BaseEntity,
        public secoes?: BaseEntity[],
    ) {
    }
}

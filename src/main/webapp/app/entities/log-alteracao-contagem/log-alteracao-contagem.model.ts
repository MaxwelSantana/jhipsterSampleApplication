import { BaseEntity } from './../../shared';

export class LogAlteracaoContagem implements BaseEntity {
    constructor(
        public id?: number,
        public campo?: string,
        public valorAntigo?: string,
        public valorNovo?: string,
        public timeStamp?: any,
        public contagem?: BaseEntity,
    ) {
    }
}

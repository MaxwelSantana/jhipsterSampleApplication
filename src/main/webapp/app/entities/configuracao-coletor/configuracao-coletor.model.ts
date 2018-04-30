import { BaseEntity } from './../../shared';

export class ConfiguracaoColetor implements BaseEntity {
    constructor(
        public id?: number,
        public cliente?: BaseEntity,
        public codigoCustomizado?: BaseEntity,
    ) {
    }
}

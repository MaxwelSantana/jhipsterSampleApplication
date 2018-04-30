import { BaseEntity } from './../../shared';

export class Mapeamento implements BaseEntity {
    constructor(
        public id?: number,
        public secaoInicial?: string,
        public secaoFinal?: string,
        public descricao?: string,
        public deposito?: boolean,
        public loja?: boolean,
        public inicialInt?: number,
        public finalInt?: number,
        public inventario?: BaseEntity,
    ) {
        this.deposito = false;
        this.loja = false;
    }
}

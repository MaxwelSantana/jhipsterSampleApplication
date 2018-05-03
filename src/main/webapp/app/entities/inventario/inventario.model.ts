import { BaseEntity } from './../../shared';

export class Inventario implements BaseEntity {
    constructor(
        public id?: number,
        public numeroLoja?: string,
        public dataInventario?: any,
        public ordemServico?: string,
        public gerenteLoja?: string,
        public liderInventario?: string,
        public qtdPessoas?: number,
        public nomeLoja?: string,
        public cliente?: BaseEntity,
        public funcionarioLider?: BaseEntity,
    ) {
    }
}

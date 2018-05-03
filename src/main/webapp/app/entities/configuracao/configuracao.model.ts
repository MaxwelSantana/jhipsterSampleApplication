import { BaseEntity } from './../../shared';

export class Configuracao implements BaseEntity {
    constructor(
        public id?: number,
        public codigoInterno?: string,
        public ean?: string,
        public descricao?: string,
        public quantidade?: string,
        public preco?: string,
        public setor?: string,
        public departamento?: string,
        public precoCusto?: string,
        public posicional?: boolean,
        public delimitador?: string,
        public desconsiderarLinhaInicial?: number,
        public desconsiderarLinhaFinal?: number,
        public stringCustomizado1?: string,
        public stringCustomizado2?: string,
        public stringCustomizado3?: string,
        public stringCustomizado4?: string,
        public stringCustomizado5?: string,
        public stringCustomizado6?: string,
        public stringCustomizado7?: string,
        public stringCustomizado8?: string,
        public stringCustomizado9?: string,
        public stringCustomizado10?: string,
        public intCustomizado1?: string,
        public intCustomizado2?: string,
        public intCustomizado3?: string,
        public intCustomizado4?: string,
        public intCustomizado5?: string,
        public intCustomizado6?: string,
        public intCustomizado7?: string,
        public intCustomizado8?: string,
        public intCustomizado9?: string,
        public intCustomizado10?: string,
        public binCustomizado1?: string,
        public binCustomizado2?: string,
        public binCustomizado3?: string,
        public cliente?: BaseEntity,
        public tipoConfiguracao?: BaseEntity,
        public codigoCustomizado?: BaseEntity,
    ) {
        this.posicional = false;
    }
}
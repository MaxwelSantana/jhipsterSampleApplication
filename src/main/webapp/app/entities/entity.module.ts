import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationClienteModule } from './cliente/cliente.module';
import { JhipsterSampleApplicationFuncionarioModule } from './funcionario/funcionario.module';
import { JhipsterSampleApplicationColetorModule } from './coletor/coletor.module';
import { JhipsterSampleApplicationTipoContagemModule } from './tipo-contagem/tipo-contagem.module';
import { JhipsterSampleApplicationStatusContagemModule } from './status-contagem/status-contagem.module';
import { JhipsterSampleApplicationInventarioModule } from './inventario/inventario.module';
import { JhipsterSampleApplicationTransmissaoModule } from './transmissao/transmissao.module';
import { JhipsterSampleApplicationSecaoModule } from './secao/secao.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhipsterSampleApplicationClienteModule,
        JhipsterSampleApplicationFuncionarioModule,
        JhipsterSampleApplicationColetorModule,
        JhipsterSampleApplicationTipoContagemModule,
        JhipsterSampleApplicationStatusContagemModule,
        JhipsterSampleApplicationInventarioModule,
        JhipsterSampleApplicationTransmissaoModule,
        JhipsterSampleApplicationSecaoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}

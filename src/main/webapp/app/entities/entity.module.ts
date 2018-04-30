import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhipsterSampleApplicationClienteModule } from './cliente/cliente.module';
import { JhipsterSampleApplicationFuncionarioModule } from './funcionario/funcionario.module';
import { JhipsterSampleApplicationColetorModule } from './coletor/coletor.module';
import { JhipsterSampleApplicationTipoContagemModule } from './tipo-contagem/tipo-contagem.module';
import { JhipsterSampleApplicationStatusContagemModule } from './status-contagem/status-contagem.module';
import { JhipsterSampleApplicationInventarioModule } from './inventario/inventario.module';
import { JhipsterSampleApplicationTransmissaoModule } from './transmissao/transmissao.module';
import { JhipsterSampleApplicationSecaoModule } from './secao/secao.module';
import { JhipsterSampleApplicationMotivoAlteracaoModule } from './motivo-alteracao/motivo-alteracao.module';
import { JhipsterSampleApplicationContagemModule } from './contagem/contagem.module';
import { JhipsterSampleApplicationLogAlteracaoContagemModule } from './log-alteracao-contagem/log-alteracao-contagem.module';
import { JhipsterSampleApplicationCadastroModule } from './cadastro/cadastro.module';
import { JhipsterSampleApplicationMapeamentoModule } from './mapeamento/mapeamento.module';
import { JhipsterSampleApplicationStatusInventarioModule } from './status-inventario/status-inventario.module';
import { JhipsterSampleApplicationLogStatusInventarioModule } from './log-status-inventario/log-status-inventario.module';
import { JhipsterSampleApplicationCampoCustomizavelColetaModule } from './campo-customizavel-coleta/campo-customizavel-coleta.module';
import { JhipsterSampleApplicationCampoCustomizavelCadastroModule } from './campo-customizavel-cadastro/campo-customizavel-cadastro.module';
import { JhipsterSampleApplicationTipoConfiguracaoModule } from './tipo-configuracao/tipo-configuracao.module';
import { JhipsterSampleApplicationCodigoCustomizavelModule } from './codigo-customizavel/codigo-customizavel.module';
import { JhipsterSampleApplicationConfiguracaoColetorModule } from './configuracao-coletor/configuracao-coletor.module';
import { JhipsterSampleApplicationConfiguracaoModule } from './configuracao/configuracao.module';
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
        JhipsterSampleApplicationMotivoAlteracaoModule,
        JhipsterSampleApplicationContagemModule,
        JhipsterSampleApplicationLogAlteracaoContagemModule,
        JhipsterSampleApplicationCadastroModule,
        JhipsterSampleApplicationMapeamentoModule,
        JhipsterSampleApplicationStatusInventarioModule,
        JhipsterSampleApplicationLogStatusInventarioModule,
        JhipsterSampleApplicationCampoCustomizavelColetaModule,
        JhipsterSampleApplicationCampoCustomizavelCadastroModule,
        JhipsterSampleApplicationTipoConfiguracaoModule,
        JhipsterSampleApplicationCodigoCustomizavelModule,
        JhipsterSampleApplicationConfiguracaoColetorModule,
        JhipsterSampleApplicationConfiguracaoModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationEntityModule {}

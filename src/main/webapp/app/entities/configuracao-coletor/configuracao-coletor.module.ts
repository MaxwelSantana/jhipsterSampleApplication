import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ConfiguracaoColetorService,
    ConfiguracaoColetorPopupService,
    ConfiguracaoColetorComponent,
    ConfiguracaoColetorDetailComponent,
    ConfiguracaoColetorDialogComponent,
    ConfiguracaoColetorPopupComponent,
    ConfiguracaoColetorDeletePopupComponent,
    ConfiguracaoColetorDeleteDialogComponent,
    configuracaoColetorRoute,
    configuracaoColetorPopupRoute,
} from './';

const ENTITY_STATES = [
    ...configuracaoColetorRoute,
    ...configuracaoColetorPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConfiguracaoColetorComponent,
        ConfiguracaoColetorDetailComponent,
        ConfiguracaoColetorDialogComponent,
        ConfiguracaoColetorDeleteDialogComponent,
        ConfiguracaoColetorPopupComponent,
        ConfiguracaoColetorDeletePopupComponent,
    ],
    entryComponents: [
        ConfiguracaoColetorComponent,
        ConfiguracaoColetorDialogComponent,
        ConfiguracaoColetorPopupComponent,
        ConfiguracaoColetorDeleteDialogComponent,
        ConfiguracaoColetorDeletePopupComponent,
    ],
    providers: [
        ConfiguracaoColetorService,
        ConfiguracaoColetorPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationConfiguracaoColetorModule {}

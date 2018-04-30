import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ConfiguracaoService,
    ConfiguracaoPopupService,
    ConfiguracaoComponent,
    ConfiguracaoDetailComponent,
    ConfiguracaoDialogComponent,
    ConfiguracaoPopupComponent,
    ConfiguracaoDeletePopupComponent,
    ConfiguracaoDeleteDialogComponent,
    configuracaoRoute,
    configuracaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...configuracaoRoute,
    ...configuracaoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ConfiguracaoComponent,
        ConfiguracaoDetailComponent,
        ConfiguracaoDialogComponent,
        ConfiguracaoDeleteDialogComponent,
        ConfiguracaoPopupComponent,
        ConfiguracaoDeletePopupComponent,
    ],
    entryComponents: [
        ConfiguracaoComponent,
        ConfiguracaoDialogComponent,
        ConfiguracaoPopupComponent,
        ConfiguracaoDeleteDialogComponent,
        ConfiguracaoDeletePopupComponent,
    ],
    providers: [
        ConfiguracaoService,
        ConfiguracaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationConfiguracaoModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    TipoConfiguracaoService,
    TipoConfiguracaoPopupService,
    TipoConfiguracaoComponent,
    TipoConfiguracaoDetailComponent,
    TipoConfiguracaoDialogComponent,
    TipoConfiguracaoPopupComponent,
    TipoConfiguracaoDeletePopupComponent,
    TipoConfiguracaoDeleteDialogComponent,
    tipoConfiguracaoRoute,
    tipoConfiguracaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tipoConfiguracaoRoute,
    ...tipoConfiguracaoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TipoConfiguracaoComponent,
        TipoConfiguracaoDetailComponent,
        TipoConfiguracaoDialogComponent,
        TipoConfiguracaoDeleteDialogComponent,
        TipoConfiguracaoPopupComponent,
        TipoConfiguracaoDeletePopupComponent,
    ],
    entryComponents: [
        TipoConfiguracaoComponent,
        TipoConfiguracaoDialogComponent,
        TipoConfiguracaoPopupComponent,
        TipoConfiguracaoDeleteDialogComponent,
        TipoConfiguracaoDeletePopupComponent,
    ],
    providers: [
        TipoConfiguracaoService,
        TipoConfiguracaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTipoConfiguracaoModule {}

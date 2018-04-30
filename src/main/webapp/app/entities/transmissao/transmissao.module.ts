import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    TransmissaoService,
    TransmissaoPopupService,
    TransmissaoComponent,
    TransmissaoDetailComponent,
    TransmissaoDialogComponent,
    TransmissaoPopupComponent,
    TransmissaoDeletePopupComponent,
    TransmissaoDeleteDialogComponent,
    transmissaoRoute,
    transmissaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...transmissaoRoute,
    ...transmissaoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TransmissaoComponent,
        TransmissaoDetailComponent,
        TransmissaoDialogComponent,
        TransmissaoDeleteDialogComponent,
        TransmissaoPopupComponent,
        TransmissaoDeletePopupComponent,
    ],
    entryComponents: [
        TransmissaoComponent,
        TransmissaoDialogComponent,
        TransmissaoPopupComponent,
        TransmissaoDeleteDialogComponent,
        TransmissaoDeletePopupComponent,
    ],
    providers: [
        TransmissaoService,
        TransmissaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationTransmissaoModule {}

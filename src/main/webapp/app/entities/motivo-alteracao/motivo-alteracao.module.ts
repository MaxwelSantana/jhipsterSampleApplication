import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    MotivoAlteracaoService,
    MotivoAlteracaoPopupService,
    MotivoAlteracaoComponent,
    MotivoAlteracaoDetailComponent,
    MotivoAlteracaoDialogComponent,
    MotivoAlteracaoPopupComponent,
    MotivoAlteracaoDeletePopupComponent,
    MotivoAlteracaoDeleteDialogComponent,
    motivoAlteracaoRoute,
    motivoAlteracaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...motivoAlteracaoRoute,
    ...motivoAlteracaoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MotivoAlteracaoComponent,
        MotivoAlteracaoDetailComponent,
        MotivoAlteracaoDialogComponent,
        MotivoAlteracaoDeleteDialogComponent,
        MotivoAlteracaoPopupComponent,
        MotivoAlteracaoDeletePopupComponent,
    ],
    entryComponents: [
        MotivoAlteracaoComponent,
        MotivoAlteracaoDialogComponent,
        MotivoAlteracaoPopupComponent,
        MotivoAlteracaoDeleteDialogComponent,
        MotivoAlteracaoDeletePopupComponent,
    ],
    providers: [
        MotivoAlteracaoService,
        MotivoAlteracaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationMotivoAlteracaoModule {}

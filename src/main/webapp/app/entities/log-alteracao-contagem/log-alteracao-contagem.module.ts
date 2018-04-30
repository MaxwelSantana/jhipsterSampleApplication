import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    LogAlteracaoContagemService,
    LogAlteracaoContagemPopupService,
    LogAlteracaoContagemComponent,
    LogAlteracaoContagemDetailComponent,
    LogAlteracaoContagemDialogComponent,
    LogAlteracaoContagemPopupComponent,
    LogAlteracaoContagemDeletePopupComponent,
    LogAlteracaoContagemDeleteDialogComponent,
    logAlteracaoContagemRoute,
    logAlteracaoContagemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...logAlteracaoContagemRoute,
    ...logAlteracaoContagemPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        LogAlteracaoContagemComponent,
        LogAlteracaoContagemDetailComponent,
        LogAlteracaoContagemDialogComponent,
        LogAlteracaoContagemDeleteDialogComponent,
        LogAlteracaoContagemPopupComponent,
        LogAlteracaoContagemDeletePopupComponent,
    ],
    entryComponents: [
        LogAlteracaoContagemComponent,
        LogAlteracaoContagemDialogComponent,
        LogAlteracaoContagemPopupComponent,
        LogAlteracaoContagemDeleteDialogComponent,
        LogAlteracaoContagemDeletePopupComponent,
    ],
    providers: [
        LogAlteracaoContagemService,
        LogAlteracaoContagemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationLogAlteracaoContagemModule {}

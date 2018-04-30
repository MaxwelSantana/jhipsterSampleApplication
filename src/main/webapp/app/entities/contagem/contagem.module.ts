import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    ContagemService,
    ContagemPopupService,
    ContagemComponent,
    ContagemDetailComponent,
    ContagemDialogComponent,
    ContagemPopupComponent,
    ContagemDeletePopupComponent,
    ContagemDeleteDialogComponent,
    contagemRoute,
    contagemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...contagemRoute,
    ...contagemPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ContagemComponent,
        ContagemDetailComponent,
        ContagemDialogComponent,
        ContagemDeleteDialogComponent,
        ContagemPopupComponent,
        ContagemDeletePopupComponent,
    ],
    entryComponents: [
        ContagemComponent,
        ContagemDialogComponent,
        ContagemPopupComponent,
        ContagemDeleteDialogComponent,
        ContagemDeletePopupComponent,
    ],
    providers: [
        ContagemService,
        ContagemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationContagemModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    StatusContagemService,
    StatusContagemPopupService,
    StatusContagemComponent,
    StatusContagemDetailComponent,
    StatusContagemDialogComponent,
    StatusContagemPopupComponent,
    StatusContagemDeletePopupComponent,
    StatusContagemDeleteDialogComponent,
    statusContagemRoute,
    statusContagemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...statusContagemRoute,
    ...statusContagemPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        StatusContagemComponent,
        StatusContagemDetailComponent,
        StatusContagemDialogComponent,
        StatusContagemDeleteDialogComponent,
        StatusContagemPopupComponent,
        StatusContagemDeletePopupComponent,
    ],
    entryComponents: [
        StatusContagemComponent,
        StatusContagemDialogComponent,
        StatusContagemPopupComponent,
        StatusContagemDeleteDialogComponent,
        StatusContagemDeletePopupComponent,
    ],
    providers: [
        StatusContagemService,
        StatusContagemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationStatusContagemModule {}

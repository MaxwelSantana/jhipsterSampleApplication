import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSampleApplicationSharedModule } from '../../shared';
import {
    SecaoService,
    SecaoPopupService,
    SecaoComponent,
    SecaoDetailComponent,
    SecaoDialogComponent,
    SecaoPopupComponent,
    SecaoDeletePopupComponent,
    SecaoDeleteDialogComponent,
    secaoRoute,
    secaoPopupRoute,
} from './';

const ENTITY_STATES = [
    ...secaoRoute,
    ...secaoPopupRoute,
];

@NgModule({
    imports: [
        JhipsterSampleApplicationSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SecaoComponent,
        SecaoDetailComponent,
        SecaoDialogComponent,
        SecaoDeleteDialogComponent,
        SecaoPopupComponent,
        SecaoDeletePopupComponent,
    ],
    entryComponents: [
        SecaoComponent,
        SecaoDialogComponent,
        SecaoPopupComponent,
        SecaoDeleteDialogComponent,
        SecaoDeletePopupComponent,
    ],
    providers: [
        SecaoService,
        SecaoPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhipsterSampleApplicationSecaoModule {}

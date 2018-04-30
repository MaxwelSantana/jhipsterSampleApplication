import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LogAlteracaoContagemComponent } from './log-alteracao-contagem.component';
import { LogAlteracaoContagemDetailComponent } from './log-alteracao-contagem-detail.component';
import { LogAlteracaoContagemPopupComponent } from './log-alteracao-contagem-dialog.component';
import { LogAlteracaoContagemDeletePopupComponent } from './log-alteracao-contagem-delete-dialog.component';

export const logAlteracaoContagemRoute: Routes = [
    {
        path: 'log-alteracao-contagem',
        component: LogAlteracaoContagemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogAlteracaoContagems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'log-alteracao-contagem/:id',
        component: LogAlteracaoContagemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogAlteracaoContagems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logAlteracaoContagemPopupRoute: Routes = [
    {
        path: 'log-alteracao-contagem-new',
        component: LogAlteracaoContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogAlteracaoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'log-alteracao-contagem/:id/edit',
        component: LogAlteracaoContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogAlteracaoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'log-alteracao-contagem/:id/delete',
        component: LogAlteracaoContagemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogAlteracaoContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

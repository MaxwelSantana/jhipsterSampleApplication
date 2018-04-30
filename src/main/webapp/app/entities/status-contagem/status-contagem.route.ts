import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StatusContagemComponent } from './status-contagem.component';
import { StatusContagemDetailComponent } from './status-contagem-detail.component';
import { StatusContagemPopupComponent } from './status-contagem-dialog.component';
import { StatusContagemDeletePopupComponent } from './status-contagem-delete-dialog.component';

export const statusContagemRoute: Routes = [
    {
        path: 'status-contagem',
        component: StatusContagemComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusContagems'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'status-contagem/:id',
        component: StatusContagemDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusContagems'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statusContagemPopupRoute: Routes = [
    {
        path: 'status-contagem-new',
        component: StatusContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'status-contagem/:id/edit',
        component: StatusContagemPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'status-contagem/:id/delete',
        component: StatusContagemDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusContagems'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

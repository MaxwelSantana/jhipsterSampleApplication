import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { LogStatusInventarioComponent } from './log-status-inventario.component';
import { LogStatusInventarioDetailComponent } from './log-status-inventario-detail.component';
import { LogStatusInventarioPopupComponent } from './log-status-inventario-dialog.component';
import { LogStatusInventarioDeletePopupComponent } from './log-status-inventario-delete-dialog.component';

export const logStatusInventarioRoute: Routes = [
    {
        path: 'log-status-inventario',
        component: LogStatusInventarioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogStatusInventarios'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'log-status-inventario/:id',
        component: LogStatusInventarioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogStatusInventarios'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const logStatusInventarioPopupRoute: Routes = [
    {
        path: 'log-status-inventario-new',
        component: LogStatusInventarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogStatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'log-status-inventario/:id/edit',
        component: LogStatusInventarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogStatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'log-status-inventario/:id/delete',
        component: LogStatusInventarioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'LogStatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

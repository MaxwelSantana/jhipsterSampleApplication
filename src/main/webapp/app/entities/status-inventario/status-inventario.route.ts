import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { StatusInventarioComponent } from './status-inventario.component';
import { StatusInventarioDetailComponent } from './status-inventario-detail.component';
import { StatusInventarioPopupComponent } from './status-inventario-dialog.component';
import { StatusInventarioDeletePopupComponent } from './status-inventario-delete-dialog.component';

export const statusInventarioRoute: Routes = [
    {
        path: 'status-inventario',
        component: StatusInventarioComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusInventarios'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'status-inventario/:id',
        component: StatusInventarioDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusInventarios'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const statusInventarioPopupRoute: Routes = [
    {
        path: 'status-inventario-new',
        component: StatusInventarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'status-inventario/:id/edit',
        component: StatusInventarioPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'status-inventario/:id/delete',
        component: StatusInventarioDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'StatusInventarios'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

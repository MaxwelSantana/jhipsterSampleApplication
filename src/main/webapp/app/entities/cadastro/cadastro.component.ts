import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Cadastro } from './cadastro.model';
import { CadastroService } from './cadastro.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-cadastro',
    templateUrl: './cadastro.component.html'
})
export class CadastroComponent implements OnInit, OnDestroy {
cadastros: Cadastro[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private cadastroService: CadastroService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.cadastroService.query().subscribe(
            (res: HttpResponse<Cadastro[]>) => {
                this.cadastros = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCadastros();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: Cadastro) {
        return item.id;
    }
    registerChangeInCadastros() {
        this.eventSubscriber = this.eventManager.subscribe('cadastroListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}

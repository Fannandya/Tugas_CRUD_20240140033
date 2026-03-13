const API_URL = "http://localhost:8080/ktp";

$(document).ready(function() {
    console.log("JQuery Ready! Menghubungkan ke: " + API_URL);
    loadKtp();

    $('#formKtp').on('submit', function(e) {
        e.preventDefault();
        saveKtp();
    });
});

function loadKtp() {
    $.ajax({
        url: API_URL,
        type: 'GET',
        success: function(data) {
            console.log("Data berhasil dimuat", data);
            let rows = '';
            data.forEach(ktp => {
                rows += `
                    <tr>
                        <td>${ktp.nomorKtp}</td>
                        <td>${ktp.namaLengkap}</td>
                        <td>${ktp.alamat}</td>
                        <td>${ktp.tanggalLahir}</td>
                        <td>${ktp.jenisKelamin}</td>
                        <td>
                            <button class="btn btn-sm btn-warning me-1" onclick="editKtp(${ktp.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteKtp(${ktp.id})">Hapus</button>
                        </td>
                    </tr>`;
            });
            $('#tabelKtp tbody').html(rows);
        },
        error: function(err) {
            console.error("Gagal load data", err);
            alert("Gagal mengambil data dari server!");
        }
    });
}

function saveKtp() {
    const id = $('#ktpId').val();
    const ktpData = {
        nomorKtp: $('#nomorKtp').val(),
        namaLengkap: $('#namaLengkap').val(),
        alamat: $('#alamat').val(),
        tanggalLahir: $('#tanggalLahir').val(),
        jenisKelamin: $('#jenisKelamin').val()
    };

    const type = id ? 'PUT' : 'POST';
    const url = id ? `${API_URL}/${id}` : API_URL;

    console.log("Mengirim data ke: " + url, ktpData);

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(ktpData),
        success: function(response) {
            alert(id ? "Data KTP Berhasil Diperbarui!" : "Data KTP Berhasil Ditambahkan!");
            resetForm();
            loadKtp();
        },
        error: function(err) {
            console.error("Error saat menyimpan", err);
            const msg = err.responseJSON ? err.responseJSON.message : "Terjadi kesalahan server";
            alert("Gagal menyimpan data: " + msg);
        }
    });
}

function deleteKtp(id) {
    if(confirm("Apakah anda yakin ingin menghapus data ini?")) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'DELETE',
            success: function() {
                alert("Data berhasil dihapus!");
                loadKtp();
            },
            error: function() {
                alert("Gagal menghapus data!");
            }
        });
    }
}

function editKtp(id) {
    $.get(`${API_URL}/${id}`, function(data) {
        $('#ktpId').val(data.id);
        $('#nomorKtp').val(data.nomorKtp);
        $('#namaLengkap').val(data.namaLengkap);
        $('#alamat').val(data.alamat);
        $('#tanggalLahir').val(data.tanggalLahir);
        $('#jenisKelamin').val(data.jenisKelamin);

        $('#btnSimpan').text('Perbarui Data').removeClass('btn-primary').addClass('btn-success');
    });
}

function resetForm() {
    $('#formKtp')[0].reset();
    $('#ktpId').val('');
    $('#btnSimpan').text('Simpan Data').removeClass('btn-success').addClass('btn-primary');
}
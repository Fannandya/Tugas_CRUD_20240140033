const API_URL = "http://localhost:8080/ktp";

$(document).ready(function() {
    loadKtp(); // Load data pas pertama kali buka

    // Submit Form (Tambah & Update)
    $('#formKtp').on('submit', function(e) {
        e.preventDefault();
        saveKtp();
    });
});

// GET: Ambil data dari API
function loadKtp() {
    $.ajax({
        url: API_URL,
        type: 'GET',
        success: function(data) {
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
                            <button class="btn btn-sm btn-warning me-2" onclick="editKtp(${ktp.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteKtp(${ktp.id})">Hapus</button>
                        </td>
                    </tr>`;
            });
            $('#tabelKtp tbody').html(rows);
        },
        error: function(err) {
            alert("Gagal mengambil data: " + err.responseJSON.message);
        }
    });
}

// POST & PUT: Simpan data
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

    $.ajax({
        url: url,
        type: type,
        contentType: 'application/json',
        data: JSON.stringify(ktpData),
        success: function() {
            alert(id ? "Data berhasil diperbarui!" : "Data berhasil ditambahkan!");
            resetForm();
            loadKtp();
        },
        error: function(err) {
            alert("Gagal menyimpan data: " + (err.responseJSON ? err.responseJSON.message : "Error server"));
        }
    });
}

// DELETE: Hapus data
function deleteKtp(id) {
    if(confirm("Yakin mau hapus data ini?")) {
        $.ajax({
            url: `${API_URL}/${id}`,
            type: 'DELETE',
            success: function() {
                alert("Data berhasil dihapus!");
                loadKtp();
            }
        });
    }
}

// Fungsi Edit (Isi form buat di-update)
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